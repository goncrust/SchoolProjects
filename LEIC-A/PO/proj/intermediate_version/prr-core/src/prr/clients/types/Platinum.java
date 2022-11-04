package prr.clients.types;

import prr.clients.Client;
import prr.communications.CommunicationType;

public class Platinum extends ClientType {
    public Platinum(Client client) {
        super(client);
    }

    @Override
    protected void upgrade() {
        // empty
    }

    @Override
    protected void downgrade() {
        if (client.getBalance() < 0)
            client.setType(client.getNormal());
        else
            client.setType(client.getGold());
    }

    @Override
    protected boolean canDowngrade() {
        return (client.getBalance() >= 0 && client.straightCommunications(CommunicationType.TEXT) == 2)
                || client.getBalance() < 0;
    }

    @Override
    protected boolean canUpgrade() {
        return false;
    }

    @Override
    public String toString() {
        return "PLATINUM";
    }
}
