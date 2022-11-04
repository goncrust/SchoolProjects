package prr.notifications;

import prr.visitors.Printer;

public class SilentToIdleNotification extends Notification {
    public SilentToIdleNotification(String terminalId) {
        super(terminalId);
    }
    
    @Override
    public void accept(Printer visitor) {
        visitor.visit(this);
    }
}
