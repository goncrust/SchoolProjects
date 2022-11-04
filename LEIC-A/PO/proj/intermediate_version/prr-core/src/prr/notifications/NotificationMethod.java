package prr.notifications;

import java.util.ArrayList;

public interface NotificationMethod {
    public void deliver(ArrayList<Notification> notifications);
}
