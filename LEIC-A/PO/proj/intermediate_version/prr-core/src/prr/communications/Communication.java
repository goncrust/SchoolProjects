package prr.communications;

import java.io.Serial;
import java.io.Serializable;

import prr.terminals.Terminal;

public class Communication implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 202217101700L;
    
    private final int key;
    private final Terminal sender;
    private final Terminal receiver;
    private int length = 0;
    private long price = 0;
    private boolean paid = false;
    private final CommunicationType type;
    private CommunicationStatus status = CommunicationStatus.ONGOING;

    /* Video/Voice constructor */
    public Communication(CommunicationType type, int key, Terminal sender,
            Terminal receiver) {
        this.type = type;
        this.key = key;
        this.sender = sender;
        this.receiver = receiver;
    }

    /* Text constructor */
    public Communication(int length, int key, Terminal sender, Terminal receiver) {
        this.length = length;
        this.type = CommunicationType.TEXT;
        this.key = key;
        this.sender = sender;
        this.receiver = receiver;
        this.status = CommunicationStatus.FINISHED;
    }

    public int getKey() {
        return key;
    }

    public Terminal getSender() {
        return sender;
    }

    public Terminal getReceiver() {
        return receiver;
    }

    public int getLength() {
        return length;
    }

    public long getPrice() {
        return price;
    }

    public boolean isPaid() {
        return paid;
    }

    public CommunicationType getType() {
        return type;
    }

    public CommunicationStatus getStatus() {
        return status;
    }

    public void endCommunication(int length, long price) {
        this.status = CommunicationStatus.FINISHED;
        this.length = length;
        this.price = price;
    }

    public void payCommunication() {
        this.paid = true;
    }
}