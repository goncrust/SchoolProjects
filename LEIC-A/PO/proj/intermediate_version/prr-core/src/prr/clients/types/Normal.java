package prr.clients.types;

import prr.clients.Client;

public class Normal extends ClientType {
    public Normal(Client client) {
        super(client);
    }

    @Override
    protected void upgrade() {
        client.setType(client.getGold());
    }

    @Override
    protected void downgrade() {
        // empty
    }

    @Override
    protected boolean canDowngrade() {
        return false;
    }

    @Override
    protected boolean canUpgrade() {
        return client.getBalance() > 500;
    }

    @Override
    public String toString() {
        return "NORMAL";
    }
}
