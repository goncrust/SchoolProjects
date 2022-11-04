package prr.notifications;

import prr.visitors.Printer;

public class OffToSilentNotification extends Notification {
    public OffToSilentNotification(String terminalId) {
        super(terminalId);
    }
    
    @Override
    public void accept(Printer visitor) {
        visitor.visit(this);
    }
}
