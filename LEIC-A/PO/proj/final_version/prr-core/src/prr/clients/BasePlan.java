package prr.clients;

import java.io.Serial;

import prr.communications.TextCommunication;
import prr.communications.VideoCommunication;
import prr.communications.VoiceCommunication;

public class BasePlan extends PriceCalculator {

    @Serial
    private static final long serialVersionUID = 202222102220L;

    private final double NORMAL_VOICE = 20;
    private final double GOLD_VOICE = 10;
    private final double PLATINUM_VOICE = 10;

    private final double NORMAL_VIDEO = 30;
    private final double GOLD_VIDEO = 20;
    private final double PLATINUM_VIDEO = 10;

    @Override
    public double visitNormal(TextCommunication text, boolean isFriend) {
        if (text.getUnits() < 50)
            return 10;
        if (text.getUnits() < 100)
            return 16;
        return 2 * text.getUnits();
    }

    @Override
    public double visitGold(TextCommunication text, boolean isFriend) {
        if (text.getUnits() < 100)
            return 10;
        return 2 * text.getUnits();
    }

    @Override
    public double visitPlatinum(TextCommunication text, boolean isFriend) {
        if (text.getUnits() < 50)
            return 0;
        return 4;
    }

    @Override
    public double visitNormal(VoiceCommunication communication, boolean isFriend) {
        if (isFriend)
            return (NORMAL_VOICE * 0.5) * communication.getUnits();
        return NORMAL_VOICE * communication.getUnits();
    }

    @Override
    public double visitGold(VoiceCommunication communication, boolean isFriend) {
        if (isFriend)
            return (GOLD_VOICE * 0.5) * communication.getUnits();
        return GOLD_VOICE * communication.getUnits();
    }

    @Override
    public double visitPlatinum(VoiceCommunication communication, boolean isFriend) {
        if (isFriend)
            return (PLATINUM_VOICE * 0.5) * communication.getUnits();
        return PLATINUM_VOICE * communication.getUnits();
    }

    @Override
    public double visitNormal(VideoCommunication communication, boolean isFriend) {
        if (isFriend)
            return (NORMAL_VIDEO * 0.5) * communication.getUnits();
        return NORMAL_VIDEO * communication.getUnits();
    }

    @Override
    public double visitGold(VideoCommunication communication, boolean isFriend) {
        if (isFriend)
            return (GOLD_VIDEO * 0.5) * communication.getUnits();
        return GOLD_VIDEO * communication.getUnits();
    }

    @Override
    public double visitPlatinum(VideoCommunication communication, boolean isFriend) {
        if (isFriend)
            return (PLATINUM_VIDEO * 0.5) * communication.getUnits();
        return PLATINUM_VIDEO * communication.getUnits();
    }

}