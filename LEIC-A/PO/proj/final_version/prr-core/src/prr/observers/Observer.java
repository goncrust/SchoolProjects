package prr.observers;

import prr.notifications.Notification;

public interface Observer {

    public void update(Notification notification);

}
