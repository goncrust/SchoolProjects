package prr.clients.types;

import prr.clients.Client;
import prr.clients.PriceCalculator;
import prr.communications.TextCommunication;
import prr.communications.VoiceCommunication;
import prr.communications.VideoCommunication;

public class Normal extends ClientType {
    public Normal(Client client) {
        super(client);
    }

    @Override
    public double accept(PriceCalculator calculator, TextCommunication communication, boolean isFriend) {
        return calculator.visitNormal(communication, isFriend);
    }

    @Override
    public double accept(PriceCalculator calculator, VoiceCommunication communication, boolean isFriend) {
        return calculator.visitNormal(communication, isFriend);
    }

    @Override
    public double accept(PriceCalculator calculator, VideoCommunication communication, boolean isFriend) {
        return calculator.visitNormal(communication, isFriend);
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
    public String getLabel() {
        return "NORMAL";
    }
}
