package prr.clients;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

import prr.terminals.Terminal;
import prr.observers.Observer;
import prr.visitors.Printable;
import prr.visitors.Printer;
import prr.clients.types.ClientType;
import prr.clients.types.Gold;
import prr.clients.types.Normal;
import prr.clients.types.Platinum;
import prr.communications.Communication;
import prr.notifications.Notification;
import prr.notifications.DeliveryMethod;
import prr.notifications.InAppNotifications;

public class Client implements Serializable, Printable, Observer {

    @Serial
    private static final long serialVersionUID = 202217101700L;

    private String name;
    private final int nif;
    private final String key;
    private PriceCalculator calculator = new BasePlan();
    private Queue<Notification> notifications = new LinkedList<>();
    private ArrayList<Terminal> terminals = new ArrayList<>();
    private final ClientType normal = new Normal(this);
    private final ClientType gold = new Gold(this);
    private final ClientType platinum = new Platinum(this);
    private ClientType type = new Normal(this);
    private DeliveryMethod deliveryMethod = new InAppNotifications(this);
    private boolean activeNotifications = true;
    private int textCount = 0;
    private int videoCount = 0;

    public Client(String name, String key, int nif) {
        this.name = name;
        this.key = key;
        this.nif = nif;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public void setPlan(PriceCalculator plan) {
        this.calculator = plan;
    }

    public void enableNotifications() {
        this.activeNotifications = true;
    }

    public void disableNotifications() {
        this.activeNotifications = false;
    }

    public ClientType getGold() {
        return gold;
    }

    public ClientType getNormal() {
        return normal;
    }

    public ClientType getPlatinum() {
        return platinum;
    }

    public int getTerminalCount() {
        return terminals.size();
    }

    public ClientType getType() {
        return this.type;
    }

    public void updateType() {
        getType().update();
    }

    public String getTypeLabel() {
        return this.type.getLabel();
    }

    public String getKey() {
        return this.key;
    }

    public int getNif() {
        return nif;
    }

    public String getName() {
        return name;
    }

    public boolean getActiveNotifications() {
        return this.activeNotifications;
    }

    public double getDebt() {
        double debt = 0;
        for (Terminal terminal : this.terminals)
            debt += terminal.getDebt();
        return debt;
    }

    public double getPaid() {
        double paid = 0;
        for (Terminal terminal : this.terminals)
            paid += terminal.getPaid();
        return paid;
    }

    public double getBalance() {
        double balance = 0;
        for (Terminal terminal : this.terminals)
            balance += terminal.getBalance();
        return balance;
    }

    public Collection<Communication> getReceivedCommunications() {
        Collection<Communication> communications = new ArrayList<>();
        for (Terminal terminal : terminals)
            (terminal.getReceivedCommunications()).forEach(communications::add);
        return communications;
    }

    public Collection<Communication> getMadeCommunications() {
        Collection<Communication> communications = new ArrayList<>();
        for (Terminal terminal : terminals)
            (terminal.getMadeCommunications()).forEach(communications::add);
        return communications;
    }

    public Collection<Terminal> getTerminals() {
        return terminals;
    }

    public Queue<Notification> getNotifications() {
        return notifications;
    }

    public void clearNotifications() {
        notifications.clear();
    }

    public void addTerminal(Terminal terminal) {
        this.terminals.add(terminal);
    }

    public void increaseTextCount() {
        if (textCount == 0)
            videoCount = 0;
        textCount++;
    }

    public void increaseVideoCount() {
        if (videoCount == 0)
            textCount = 0;
        videoCount++;
    }

    public void resetCount() {
        textCount = 0;
        videoCount = 0;
    }

    public int getTextCount() {
        return textCount;
    }

    public int getVideoCount() {
        return videoCount;
    }

    public PriceCalculator getCalculator() {
        return calculator;
    }

    public void queueNotification(Notification notification) {
        notifications.add(notification);
    }

    @Override
    public void update(Notification notification) {
        if (activeNotifications)
            deliveryMethod.deliver(notification);
    }

    @Override
    public void accept(Printer visitor) {
        visitor.visit(this);
    }

}
