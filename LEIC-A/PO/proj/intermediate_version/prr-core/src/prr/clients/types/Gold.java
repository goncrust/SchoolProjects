package prr.clients.types;

import prr.clients.Client;
import prr.communications.CommunicationType;

public class Gold extends ClientType {
    public Gold(Client client) {
        super(client);
    }

    @Override
    protected void upgrade() {
        client.setType(client.getPlatinum());
    }

    @Override
    protected void downgrade() {
        client.setType(client.getNormal());
    }

    @Override
    protected boolean canDowngrade() {
        return client.getBalance() < 0;
    }

    @Override
    protected boolean canUpgrade() {
        return client.getBalance() >= 0 && client.straightCommunications(CommunicationType.VIDEO) == 5;
    }

    @Override
    public String toString() {
        return "GOLD";
    }
}
