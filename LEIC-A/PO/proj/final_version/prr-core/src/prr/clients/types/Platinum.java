package prr.clients.types;

import prr.clients.Client;
import prr.clients.PriceCalculator;
import prr.communications.TextCommunication;
import prr.communications.VoiceCommunication;
import prr.communications.VideoCommunication;

public class Platinum extends ClientType {
    public Platinum(Client client) {
        super(client);
    }

    @Override
    public double accept(PriceCalculator calculator, TextCommunication communication, boolean isFriend) {
        return calculator.visitPlatinum(communication, isFriend);
    }

    @Override
    public double accept(PriceCalculator calculator, VoiceCommunication communication, boolean isFriend) {
        return calculator.visitPlatinum(communication, isFriend);
    }

    @Override
    public double accept(PriceCalculator calculator, VideoCommunication communication, boolean isFriend) {
        return calculator.visitPlatinum(communication, isFriend);
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
        return (client.getBalance() >= 0 && client.getTextCount() == 2)
                || client.getBalance() < 0;
    }

    @Override
    protected boolean canUpgrade() {
        return false;
    }

    @Override
    public String getLabel() {
        return "PLATINUM";
    }
}
