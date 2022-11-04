package prr.notifications;

import java.io.Serial;
import java.io.Serializable;

public class Notification implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 202217101700L;
    
    private NotificationType type;
    private int terminalId;

    public Notification(NotificationType type, int terminalId) {
        this.type = type;
        this.terminalId = terminalId;
    }
    
    public NotificationType getType() {
        return type;
    }

    public int getTerminalId() {
        return terminalId;
    }

}
