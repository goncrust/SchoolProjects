package prr.communications;

import java.io.Serial;
import java.io.Serializable;

import prr.terminals.Terminal;
import prr.visitors.Printable;

public abstract class Communication implements Serializable, Printable {

    @Serial
    private static final long serialVersionUID = 202217101700L;

    private final int key;
    private final Terminal sender;
    private final Terminal receiver;
    private boolean paid = false;
    private boolean finished = false;
    private int units;
    private double price = 0;

    public Communication(int key, Terminal sender, Terminal receiver, int units) {
        this.key = key;
        this.sender = sender;
        this.receiver = receiver;
        this.units = units;
    }

    public int getKey() {
        return key;
    }

    public Terminal getSender() {
        return sender;
    }

    public String getSenderKey() {
        return sender.getKey();
    }

    public Terminal getReceiver() {
        return receiver;
    }

    public String getReceiverKey() {
        return receiver.getKey();
    }

    public double getPrice() {
        return price;
    }

    public boolean isPaid() {
        return paid;
    }

    public boolean isFinished() {
        return finished;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public void finish() {
        finished = true;
    }

    public void payCommunication() {
        this.paid = true;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract void updatePrice(boolean isFriend);

}