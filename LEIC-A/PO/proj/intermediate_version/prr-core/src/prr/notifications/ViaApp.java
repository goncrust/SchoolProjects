package prr.notifications;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class ViaApp implements NotificationMethod, Serializable {
    
    @Serial
    private static final long serialVersionUID = 202217101700L;
    
    @Override
    public void deliver(ArrayList<Notification> notifications) {

    }
}
