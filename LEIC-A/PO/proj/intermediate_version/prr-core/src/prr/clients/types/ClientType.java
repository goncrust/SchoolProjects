package prr.clients.types;

import java.io.Serial;
import java.io.Serializable;

import prr.clients.Client;

public abstract class ClientType implements Serializable {

    @Serial
    private static final long serialVersionUID = 202217101700L;

    protected final Client client;

    public ClientType(Client client) {
        this.client = client;
    }

    abstract protected void upgrade();

    abstract protected void downgrade();

    abstract protected boolean canDowngrade();

    abstract protected boolean canUpgrade();

    public void update() {
        if (canUpgrade())
            upgrade();
        else if (canDowngrade())
            downgrade();
    }
}
