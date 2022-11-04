package prr.notifications;

import java.io.Serializable;

import prr.visitors.Printable;

public abstract class Notification implements Serializable, Printable {

    private final String terminalId;

    public Notification(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getId() {
        return terminalId;
    }
}
