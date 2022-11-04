package prr.clients;

import java.io.Serial;
import java.io.Serializable;

import prr.communications.Communication;
import prr.communications.CommunicationType;

public abstract class ClientPlan implements Serializable {

    @Serial
    private static final long serialVersionUID = 202217101700L;

    protected final Client client;

    public ClientPlan(Client client) {
        this.client = client;
    }

    protected abstract long getTextPrice(Communication communication);

    protected abstract long getCallPrice(Communication communication);

    protected abstract long getVideoPrice(Communication communication);

    public long getPrice(Communication communication) {
        CommunicationType type = communication.getType();
        if (type == CommunicationType.TEXT)
            return getTextPrice(communication);
        if (type == CommunicationType.CALL)
            return getCallPrice(communication);
        return getVideoPrice(communication);
    }
}
