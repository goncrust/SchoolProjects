package prr.notifications;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class NotificationManager implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 202217101700L;
    
    private NotificationMethod method = new ViaApp();
    private final ArrayList<Notification> notifications = new ArrayList<>();
    
    public NotificationManager(NotificationMethod method) {
        this.method = method;
    }

    public void clearNotifications() {

    }

    public void addNotification() {

    }

    public void deliver() {
        method.deliver(notifications);
    }
    
}
