package prr.clients.types;

import prr.clients.Client;
import prr.clients.PriceCalculator;
import prr.communications.TextCommunication;
import prr.communications.VoiceCommunication;
import prr.communications.VideoCommunication;

public class Gold extends ClientType {
    public Gold(Client client) {
        super(client);
    }

    @Override
    public double accept(PriceCalculator calculator, TextCommunication communication, boolean isFriend) {
        return calculator.visitGold(communication, isFriend);
    }

    @Override
    public double accept(PriceCalculator calculator, VoiceCommunication communication, boolean isFriend) {
        return calculator.visitGold(communication, isFriend);
    }

    @Override
    public double accept(PriceCalculator calculator, VideoCommunication communication, boolean isFriend) {
        return calculator.visitGold(communication, isFriend);
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
        return client.getBalance() >= 0 && client.getVideoCount() == 5;
    }

    @Override
    public String getLabel() {
        return "GOLD";
    }

}
