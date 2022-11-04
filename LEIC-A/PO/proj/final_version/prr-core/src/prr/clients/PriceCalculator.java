package prr.clients;

import java.io.Serializable;

import prr.communications.TextCommunication;
import prr.communications.VideoCommunication;
import prr.communications.VoiceCommunication;

public abstract class PriceCalculator implements Serializable {

    public abstract double visitNormal(TextCommunication text, boolean isFriend);

    public abstract double visitGold(TextCommunication text, boolean isFriend);

    public abstract double visitPlatinum(TextCommunication text, boolean isFriend);

    public abstract double visitNormal(VoiceCommunication communication, boolean isFriend);

    public abstract double visitGold(VoiceCommunication communication, boolean isFriend);

    public abstract double visitPlatinum(VoiceCommunication communication, boolean isFriend);

    public abstract double visitNormal(VideoCommunication communication, boolean isFriend);

    public abstract double visitGold(VideoCommunication communication, boolean isFriend);

    public abstract double visitPlatinum(VideoCommunication communication, boolean isFriend);
}
