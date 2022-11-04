package prr;

import java.io.Serial;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import prr.util.NaturalLanguageTextComparator;
import prr.visitors.Printer;
import prr.visitors.Selector;
import prr.clients.Client;
import prr.communications.Communication;
import prr.communications.TextCommunication;
import prr.communications.VideoCommunication;
import prr.communications.VoiceCommunication;
import prr.terminals.FancyTerminal;
import prr.terminals.BasicTerminal;
import prr.terminals.Terminal;
import prr.exceptions.UnrecognizedEntryException;
import prr.exceptions.UnrecognizedTerminalTypeException;
import prr.notifications.Notification;
import prr.exceptions.ClientDoesntExistException;
import prr.exceptions.ClientExistsException;
import prr.exceptions.IncorrectTerminalKeyException;
import prr.exceptions.InvalidEntryException;
import prr.exceptions.NotificationsAlreadyDisabledException;
import prr.exceptions.NotificationsAlreadyEnabledException;
import prr.exceptions.TerminalDoesntExistException;
import prr.exceptions.TerminalExistsException;

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

    /** Serial number for serialization. */
    @Serial
    private static final long serialVersionUID = 202208091753L;

    /** Clients list, sorted by key */
    private Map<String, Client> clients = new TreeMap<>(new NaturalLanguageTextComparator());

    /** Terminals list, sorted by key */
    private Map<String, Terminal> terminals = new TreeMap<>(new NaturalLanguageTextComparator());

    /** Communications counter, used for generating communication keys */
    private int communicationKey = 0;

    /** Something as changed since last save */
    private boolean unsaved = false;

    /**
     * Register that something changed
     */
    public void changed() {
        this.unsaved = true;
    }

    /**
     * Error saving, remains unsaved
     */
    public void failedSave() {
        this.unsaved = true;
    }

    /**
     * Register that all was saved
     */
    public void saved() {
        this.unsaved = false;
    }

    /**
     * Getter for unsaved variable
     */
    public boolean isUnsaved() {
        return this.unsaved;
    }

    /**
     * Get and increment the communication key tracker
     */
    public int getCommunicationKey() {
        return ++communicationKey;
    }

    /**
     * Generate a new {@link TextCommunication}
     * 
     * @param sender   new message's sender
     * @param receiver new message's receiver
     * @param message  message contents
     */
    public TextCommunication newTextCommunication(Terminal sender, Terminal receiver, String message,
            boolean isFriend) {
        return new TextCommunication(getCommunicationKey(), sender, receiver, message, isFriend);
    }

    /**
     * Generate a new {@link VoiceCommunication}
     * 
     * @param sender   new message's sender
     * @param receiver new message's receiver
     */
    public VoiceCommunication newVoiceCommunication(Terminal sender, Terminal receiver) {
        return new VoiceCommunication(getCommunicationKey(), sender, receiver);
    }

    /**
     * Generate a new {@link VideoCommunication}
     * 
     * @param sender   new message's sender
     * @param receiver new message's receiver
     */
    public VideoCommunication newVideoCommunication(Terminal sender, Terminal receiver) {
        return new VideoCommunication(getCommunicationKey(), sender, receiver);
    }

    /**
     * Register a new client in the network
     * 
     * @param name new client's name
     * @param key  new client's key
     * @param nif  new client's tax id
     * @throws ClientExistsException if the given key is already in use
     */
    public void registerClient(String key, String name, int nif) throws ClientExistsException {
        if (clients.containsKey(key))
            throw new ClientExistsException(key);
        clients.put(key, new Client(name, key, nif));
        changed();
    }

    /**
     * Checks if a client exists
     * 
     * @param key the key that identifies the client
     * @throws ClientDoesntExistException if the given key can't be found
     */
    public void checkClient(String key) throws ClientDoesntExistException {
        if (!clients.containsKey(key))
            throw new ClientDoesntExistException(key);
    }

    /**
     * Checks if a terminal exists
     * 
     * @param key the key that identifies the terminal
     * @throws TerminalDoesntExistException if the given key can't be found
     */
    public void checkTerminal(String key) throws TerminalDoesntExistException {
        if (!terminals.containsKey(key))
            throw new TerminalDoesntExistException(key);
    }

    /**
     * Get a client
     * 
     * @param key the key that identifies the client
     * @return The {@link Client} with the matching key
     * @throws ClientDoesntExistException if the given key can't be found
     */
    public Client getClient(String key) throws ClientDoesntExistException {
        checkClient(key);
        return clients.get(key);
    }

    /**
     * Enables client notifications
     * 
     * @param key the key that identifies the client
     * @throws ClientDoesntExistException           if the given key can't be found
     * @throws NotificationsAlreadyEnabledException if the notifications are already
     *                                              enabled
     */
    public void enableNotifications(String key)
            throws ClientDoesntExistException, NotificationsAlreadyEnabledException {
        Client client = getClient(key);
        if (client.getActiveNotifications())
            throw new NotificationsAlreadyEnabledException();
        client.enableNotifications();
    }

    /**
     * Disables client notifications
     * 
     * @param key the key that identifies the client
     * @throws ClientDoesntExistException           if the given key can't be found
     * @throws NotificationsAlreadyEnabledException if the notifications are already
     *                                              disabled
     */
    public void disableNotifications(String key)
            throws ClientDoesntExistException, NotificationsAlreadyDisabledException {
        Client client = getClient(key);
        if (!client.getActiveNotifications())
            throw new NotificationsAlreadyDisabledException();
        client.disableNotifications();
    }

    /**
     * Gets client debt
     * 
     * @param key the key that identifies the client
     * @throws ClientDoesntExistException if the given key can't be found
     */
    public double getClientDebt(String key) throws ClientDoesntExistException {
        return getClient(key).getDebt();
    }

    /**
     * Gets client payments
     * 
     * @param key the key that identifies the client
     * @throws ClientDoesntExistException if the given key can't be found
     */
    public double getClientPayments(String key) throws ClientDoesntExistException {
        return getClient(key).getPaid();
    }

    /**
     * Gets global payments
     * 
     * @return Total paid by all clients
     */
    public long getGlobalPayments() {
        long total = 0;
        for (Client c : clients.values())
            total += c.getPaid();
        return total;
    }

    /**
     * Gets global debts
     * 
     * @return Total debt of all clients
     */
    public long getGlobalDebts() {
        long total = 0;
        for (Client c : clients.values())
            total += c.getDebt();
        return total;
    }

    /**
     * Get a terminal
     * 
     * @param key the key that identifies the terminal
     * @return The {@link Terminal} with the matching key
     * @throws TerminalDoesntExistException if the given key can't be found
     */
    public Terminal getTerminal(String key) throws TerminalDoesntExistException {
        checkTerminal(key);
        return terminals.get(key);
    }

    /**
     * Register a new terminal in the network
     * 
     * @param key    new terminal's key
     * @param type   new terminal's type
     * @param client new terminal's owner
     * @param state  new terminal's initial state
     * @return the newly created terminal
     * @throws TerminalExistsException    if the given key is already in use
     * @throws ClientDoesntExistException if the given client doesnt exist
     */
    public Terminal registerTerminal(String key, String type, String client)
            throws TerminalExistsException, IncorrectTerminalKeyException, ClientDoesntExistException,
            UnrecognizedTerminalTypeException {

        if (terminals.containsKey(key))
            throw new TerminalExistsException(key);

        if (!key.matches("[0-9]{6}"))
            throw new IncorrectTerminalKeyException(key);

        Client owner = getClient(client);
        Terminal newTerminal;

        if (type.equals("FANCY"))
            newTerminal = new FancyTerminal(key, clients.get(client));
        else if (type.equals("BASIC"))
            newTerminal = new BasicTerminal(key, clients.get(client));
        else
            throw new UnrecognizedTerminalTypeException(type);

        owner.addTerminal(newTerminal);
        terminals.put(key, newTerminal);

        changed();

        return newTerminal;
    }

    /**
     * Visit the communications received by all selected client with a printer
     * 
     * @param selector
     * @param visitor
     */
    public void acceptReceivedCommunicationsPrinter(Selector<Client> selector, Printer visitor) {
        for (Client client : clients.values())
            if (selector.ok(client))
                for (Communication communication : client.getReceivedCommunications())
                    communication.accept(visitor);
    }

    /**
     * Visit the communications made by all selected client with a printer
     * 
     * @param selector
     * @param visitor
     */
    public void acceptMadeCommunicationsPrinter(Selector<Client> selector, Printer visitor) {
        for (Client client : clients.values())
            if (selector.ok(client))
                for (Communication communication : client.getMadeCommunications())
                    communication.accept(visitor);
    }

    /**
     * Visit selected clients with a printer
     * 
     * @param selector
     * @param visitor
     */
    public void acceptSimpleClientPrinter(Selector<Client> selector, Printer visitor) {
        for (Client c : clients.values())
            if (selector.ok(c))
                c.accept(visitor);
    }

    /**
     * Visit selected clients and it's in app notifications with a printer
     * 
     * @param selector
     * @param visitor
     */
    public void acceptVerboseClientPrinter(Selector<Client> selector, Printer visitor) {
        for (Client c : clients.values())
            if (selector.ok(c)) {
                c.accept(visitor);
                Notification n;
                while ((n = c.getNotifications().poll()) != null)
                    n.accept(visitor);
            }
    }

    /**
     * Visit selected terminals with a printer
     * 
     * @param selector
     * @param visitor
     */
    public void acceptTerminalPrinter(Selector<Terminal> selector, Printer visitor) {
        for (Terminal t : terminals.values())
            if (selector.ok(t))
                t.accept(visitor);
    }

    /**
     * Loads a client onto the network from an array of fields
     * 
     * @param fields the line fields
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws InvalidEntryException      if some entry doesn't respect the rules
     *                                    (repeated keys, invalid keys, etc.)
     */
    private void importClient(String[] fields) throws UnrecognizedEntryException, InvalidEntryException {
        if (fields.length != 4)
            throw new UnrecognizedEntryException(String.join("|", fields));
        try {
            registerClient(fields[1], fields[2], Integer.parseInt(fields[3]));
        } catch (ClientExistsException | NumberFormatException e) {
            throw new InvalidEntryException(String.join("|", fields), e);
        }
    }

    /**
     * Loads a terminal onto the network from an array of fields
     * 
     * @param fields the line fields
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws InvalidEntryException      if some entry doesn't respect the rules
     *                                    (repeated keys, invalid keys, etc.)
     */
    private void importTerminal(String[] fields) throws UnrecognizedEntryException, InvalidEntryException {
        if (fields.length != 4 | !fields[3].matches("^(ON|OFF|SILENCE)"))
            throw new UnrecognizedEntryException(String.join("|", fields));
        try {
            Terminal terminal = registerTerminal(fields[1], fields[0], fields[2]);
            switch (fields[3]) {
                case "ON":
                    break;
                case "OFF":
                    terminal.setState(terminal.getOffState());
                    break;
                case "SILENCE":
                    terminal.setState(terminal.getSilenceState());
                    break;
                default:
                    throw new InvalidEntryException(String.join("|", fields));
            }
        } catch (TerminalExistsException | IncorrectTerminalKeyException | ClientDoesntExistException
                | UnrecognizedTerminalTypeException e) {
            throw new InvalidEntryException(String.join("|", fields), e);
        }
    }

    /**
     * Loads a list of friends onto the network from an array of fields
     * 
     * @param fields the line fields
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws InvalidEntryException      if some entry doesn't respect the rules
     *                                    (repeated keys, invalid keys, etc.)
     */
    private void importFriends(String[] fields) throws UnrecognizedEntryException, InvalidEntryException {
        if (fields.length != 3)
            throw new UnrecognizedEntryException(String.join("|", fields));

        try {
            Terminal terminal = getTerminal(fields[1]);
            String[] friends = fields[2].split(",");
            for (String friend : friends) {
                checkTerminal(friend);
                terminal.addFriend(friend);
            }

            changed();
        } catch (TerminalDoesntExistException e) {
            throw new InvalidEntryException(String.join("|", fields), e);
        }
    }

    /**
     * Read the first field of a line and check what object is supposed to be
     * imported
     * 
     * @param fields the line fields
     * @throws UnrecognizedEntryException if the first field isn't recognized
     */
    private void importObject(String[] fields) throws UnrecognizedEntryException, InvalidEntryException {
        if (fields[0].matches("^(CLIENT)"))
            importClient(fields);
        else if (fields[0].matches("^(BASIC|FANCY)"))
            importTerminal(fields);
        else if (fields[0].matches("^(FRIENDS)"))
            importFriends(fields);
        else
            throw new UnrecognizedEntryException(String.join("|", fields));
    }

    /**
     * Read text input file and create corresponding domain entities.
     * 
     * @param filename name of the text input file
     * @throws UnrecognizedEntryException if some entry is not correct
     * @throws InvalidEntryException      if some entry doesn't respect the rules
     *                                    (repeated keys, invalid keys, etc.)
     * @throws IOException                if there is an IO error while processing
     *                                    the text file
     */
    void importFile(String filename) throws UnrecognizedEntryException, InvalidEntryException, IOException {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while ((line = reader.readLine()) != null)
                importObject(line.split("\\|"));
        }
    }
}