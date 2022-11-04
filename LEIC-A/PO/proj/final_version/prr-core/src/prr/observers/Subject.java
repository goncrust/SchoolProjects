package prr.observers;

import prr.notifications.Notification;

public interface Subject {

    public void registerInteractiveCommunicationObserver(Observer observer);

    public void registerTextCommunicationObserver(Observer observer);

    public void notifyAllObservers(Notification notification);

    public void notifyInteractiveCommunicationObservers(Notification notification);

    public void notifyTextCommunicationObservers(Notification notification);
}
