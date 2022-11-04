package prr.terminals;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import prr.Network;
import prr.clients.Client;
import prr.notifications.Notification;
import prr.observers.Observer;
import prr.observers.Subject;
import prr.communications.Communication;
import prr.communications.InteractiveCommunication;
import prr.communications.TextCommunication;
import prr.communications.VideoCommunication;
import prr.communications.VoiceCommunication;
import prr.exceptions.BusyTerminalException;
import prr.exceptions.IdleTerminalException;
import prr.exceptions.InvalidCommunicationException;
import prr.exceptions.InvalidDestinationException;
import prr.exceptions.InvalidOriginException;
import prr.exceptions.NoOngoingCommunicationException;
import prr.exceptions.OffTerminalException;
import prr.exceptions.SilencedTerminalException;
import prr.exceptions.TerminalDoesntExistException;
import prr.terminals.states.*;
import prr.visitors.Printable;
import prr.visitors.Printer;

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable, Printable, Subject {

    /** Serial number for serialization. */
    @Serial
    private static final long serialVersionUID = 202208091753L;

    private String key;
    private Client owner;
    private TreeMap<Integer, Communication> receivedCommunications = new TreeMap<>();
    private TreeMap<Integer, Communication> madeCommunications = new TreeMap<>();
    private ArrayList<String> friends = new ArrayList<>();
    private ArrayList<Observer> textCommunicationObservers = new ArrayList<>();
    private ArrayList<Observer> interactiveCommunicationObservers = new ArrayList<>();
    private InteractiveCommunication currentCommunication;

    private final TerminalState busyState = new Busy(this);
    private final TerminalState idleState = new Idle(this);
    private final TerminalState offState = new Off(this);
    private final TerminalState silenceState = new Silence(this);
    private TerminalState savedState;
    private TerminalState state;

    public Terminal(String key, Client owner) {
        this.key = key;
        this.owner = owner;

        // Default state
        this.state = idleState;
    }

    public String getKey() {
        return this.key;
    }

    public void setState(TerminalState state) {
        this.state = state;
    }

    public void saveState(TerminalState state) {
        savedState = state;
    }

    public TerminalState getSavedState() {
        return savedState;
    }

    public Client getOwner() {
        return this.owner;
    }

    public String getOwnerKey() {
        return this.owner.getKey();
    }

    public Collection<String> getFriends() {
        return friends;
    }

    public TerminalState getState() {
        return this.state;
    }

    public String getStateLabel() {
        return this.state.getLabel();
    }

    public TerminalState getBusyState() {
        return this.busyState;
    }

    public TerminalState getIdleState() {
        return this.idleState;
    }

    public TerminalState getOffState() {
        return this.offState;
    }

    public TerminalState getSilenceState() {
        return this.silenceState;
    }

    public Collection<Communication> getReceivedCommunications() {
        return receivedCommunications.values();
    }

    public Collection<Communication> getMadeCommunications() {
        return madeCommunications.values();
    }

    public void registerReceivedCommunication(Communication communication) {
        receivedCommunications.put(communication.getKey(), communication);
    }

    public void registerMadeCommunication(Communication communication) {
        madeCommunications.put(communication.getKey(), communication);
    }

    public void setCurrentCommunication(InteractiveCommunication currentCommunication) {
        this.currentCommunication = currentCommunication;
    }

    public void toSilence() throws SilencedTerminalException, BusyTerminalException {
        state.toSilence();
    }

    public void toOff() throws BusyTerminalException, OffTerminalException {
        state.toOff();
    }

    public void toOn() throws BusyTerminalException, SilencedTerminalException, IdleTerminalException {
        state.toOn();
    }

    public boolean isSilenced() {
        return getState().equals(getSilenceState());
    }

    public boolean isOff() {
        return getState().equals(getOffState());
    }

    public boolean isBusy() {
        return getState().equals(getBusyState());
    }

    public boolean isIdle() {
        return getState().equals(getIdleState());
    }

    public boolean isFriend(String key) {
        return friends.contains(key);
    }

    public void addFriend(String friend) {
        if (!friends.contains(friend))
            friends.add(friend);
    }

    public void addFriend(String friend, Network network) throws TerminalDoesntExistException {
        network.checkTerminal(friend);
        if (!isFriend(friend) && !friend.equals(getKey()))
            friends.add(friend);
    }

    public void removeFriend(String friend, Network network) throws TerminalDoesntExistException {
        network.checkTerminal(friend);
        if (friends.contains(friend))
            friends.remove(friend);
    }

    public void sendText(String receiverKey, String text, Network network)
            throws TerminalDoesntExistException, OffTerminalException {
        Terminal receiver = network.getTerminal(receiverKey);
        TextCommunication communication = network.newTextCommunication(this, receiver, text, isFriend(receiverKey));
        receiver.receiveText(communication);
        registerMadeCommunication(communication);
        getOwner().increaseTextCount();
        getOwner().updateType();
    }

    public void receiveText(TextCommunication text) throws OffTerminalException {
        if (isOff()) {
            registerTextCommunicationObserver((text.getSender()).getOwner());
            throw new OffTerminalException(getKey());
        }
        registerReceivedCommunication(text);
    }

    public void startInteractiveCommunication(String receiverKey, String type, Network network)
            throws TerminalDoesntExistException, InvalidOriginException,
            InvalidDestinationException, BusyTerminalException, OffTerminalException, SilencedTerminalException {
        if (receiverKey.equals(getKey()))
            throw new InvalidDestinationException(receiverKey, type);
        Terminal receiver = network.getTerminal(receiverKey);
        if (type.equals("VOICE"))
            startVoiceCommunication(receiver, network);
        else if (type.equals("VIDEO"))
            startVideoCommunication(receiver, network);
        else
            throw new InvalidOriginException(getKey(), type);
    }

    private void startVoiceCommunication(Terminal receiver, Network network)
            throws OffTerminalException, BusyTerminalException, SilencedTerminalException {
        VoiceCommunication communication = network.newVoiceCommunication(this, receiver);
        receiver.receiveVoiceCommunication(communication);
        getState().startCommunication();
        setCurrentCommunication(communication);
        registerMadeCommunication(communication);
        getOwner().resetCount();
    }

    public void receiveVoiceCommunication(VoiceCommunication communication)
            throws OffTerminalException, SilencedTerminalException, BusyTerminalException {
        try {
            getState().receiveCommunication();
        } catch (OffTerminalException | BusyTerminalException | SilencedTerminalException e) {
            registerInteractiveCommunicationObserver((communication.getSender()).getOwner());
            throw e;
        }
        setCurrentCommunication(communication);
        registerReceivedCommunication(communication);
    }

    protected abstract void startVideoCommunication(Terminal receiver, Network network)
            throws InvalidOriginException, InvalidDestinationException, BusyTerminalException,
            SilencedTerminalException, OffTerminalException;

    public abstract void receiveVideoCommunication(VideoCommunication communication)
            throws OffTerminalException, SilencedTerminalException, BusyTerminalException, InvalidDestinationException;

    public double endCommunication(int units) {
        Terminal receiver = currentCommunication.getReceiver();
        getState().endCommunication();
        receiver.notifyEndedCommunication();
        double price = currentCommunication.endCommunication(units, isFriend(receiver.getKey()));
        getOwner().updateType();
        return price;
    }

    public void notifyEndedCommunication() {
        getState().endCommunication();
    }

    /**
     * Checks if this terminal can end the current interactive communication.
     *
     * @return true if this terminal is busy (i.e., it has an active interactive
     *         communication) and
     *         it was the originator of this communication.
     **/
    public boolean canEndCurrentCommunication() {
        if (currentCommunication == null)
            return false;
        return isBusy() && madeCommunications.keySet().contains(currentCommunication.getKey());
    }

    /**
     * Checks if this terminal can start a new communication.
     *
     * @return true if this terminal is neither off neither busy, false otherwise.
     **/
    public boolean canStartCommunication() {
        return !isBusy() && !isOff();
    }

    public InteractiveCommunication getCurrentCommunication() {
        return this.currentCommunication;
    }

    public double getBalance() {
        return getPaid() - getDebt();
    }

    public double getDebt() {
        double total = 0;
        for (Communication c : madeCommunications.values()) {
            if (!c.isPaid())
                total += c.getPrice();
        }

        return total;
    }

    public double getPaid() {
        double total = 0;
        for (Communication c : madeCommunications.values()) {
            if (c.isPaid())
                total += c.getPrice();
        }

        return total;
    }

    public void payCommunication(int key) throws InvalidCommunicationException {
        Communication communication = madeCommunications.get(key);

        if (communication == null || !communication.isFinished() || communication.isPaid())
            throw new InvalidCommunicationException();

        communication.payCommunication();
        getOwner().updateType();
    }

    public int getCommunicationCount() {
        return receivedCommunications.size() + madeCommunications.size();
    }

    public void acceptCurrentCommunicationPrinter(Printer printer) throws NoOngoingCommunicationException {
        if (currentCommunication == null || currentCommunication.isFinished())
            throw new NoOngoingCommunicationException();
        currentCommunication.accept(printer);
    }

    private void clearInteractiveCommunicationObservers() {
        interactiveCommunicationObservers.clear();
    }

    private void clearTextCommunicationObservers() {
        textCommunicationObservers.clear();
    }

    @Override
    public void registerInteractiveCommunicationObserver(Observer observer) {
        if (!interactiveCommunicationObservers.contains(observer))
            interactiveCommunicationObservers.add(observer);
    }

    @Override
    public void registerTextCommunicationObserver(Observer observer) {
        if (!textCommunicationObservers.contains(observer))
            textCommunicationObservers.add(observer);
    }

    @Override
    public void notifyAllObservers(Notification notification) {
        for (Observer observer : textCommunicationObservers)
            observer.update(notification);
        for (Observer observer : interactiveCommunicationObservers)
            if (!textCommunicationObservers.contains(observer))
                observer.update(notification);
        clearTextCommunicationObservers();
        clearInteractiveCommunicationObservers();
    }

    @Override
    public void notifyInteractiveCommunicationObservers(Notification notification) {
        for (Observer observer : interactiveCommunicationObservers)
            observer.update(notification);
        clearInteractiveCommunicationObservers();
    }

    @Override
    public void notifyTextCommunicationObservers(Notification notification) {
        for (Observer observer : textCommunicationObservers)
            observer.update(notification);
        clearTextCommunicationObservers();
    }

}
