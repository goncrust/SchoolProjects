package prr.terminals;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringJoiner;

import prr.clients.Client;
import prr.communications.Communication;
import prr.terminals.states.*;

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable {

    /** Serial number for serialization. */
    @Serial
    private static final long serialVersionUID = 202208091753L;

    // attributes
    private String key;
    private Client owner;
    private ArrayList<Communication> receivedComms = new ArrayList<>();
    private ArrayList<Communication> madeComms = new ArrayList<>();
    private ArrayList<String> friends = new ArrayList<>();

    // Terminal State
    private final TerminalState busyState = new Busy(this);
    private final TerminalState idleState = new Idle(this);
    private final TerminalState offState = new Off(this);
    private final TerminalState silenceState = new Silence(this);
    private TerminalState state;

    // contructor(s)
    public Terminal(String key, Client owner) {
        this.key = key;
        this.owner = owner;

        // Default state
        this.state = idleState;
    }

    public String getKey() {
        return this.key;
    }

    public void setState(TerminalState state) {
        this.state = state;
    }

    public TerminalState getState() {
        return this.state;
    }

    public TerminalState getBusyState() {
        return this.busyState;
    }

    public TerminalState getIdleState() {
        return this.idleState;
    }

    public TerminalState getOffState() {
        return this.offState;
    }

    public TerminalState getSilenceState() {
        return this.silenceState;
    }

    public void toSilence() {
        state.toSilence();
    }

    public void toOff() {
        state.toOff();
    }

    public void toBusy() {
        state.toBusy();
    }

    public void toIdle() {
        state.toIdle();
    }

    public boolean isSilence() {
        return getState().equals(getSilenceState());
    }

    public boolean isOff() {
        return getState().equals(getOffState());
    }

    public boolean isBusy() {
        return getState().equals(getBusyState());
    }

    public boolean isIdle() {
        return getState().equals(getIdleState());
    }

    public void addFriend(String friend) {
        if (!friends.contains(friend))
            friends.add(friend);
    }

    /**
     * Checks if this terminal can end the current interactive communication.
     *
     * @return true if this terminal is busy (i.e., it has an active interactive
     *         communication) and
     *         it was the originator of this communication.
     **/
    public boolean canEndCurrentCommunication() {
        return isBusy(); // TODO && it was the originator of this communication.
    }

    /**
     * Checks if this terminal can start a new communication.
     *
     * @return true if this terminal is neither off neither busy, false otherwise.
     **/
    public boolean canStartCommunication() {
        return !isBusy() && !isOff();
    }

    public long getBalance() {
        return getPaid() - getOwed();
    }

    public long getOwed() {
        long total = 0;
        for (Communication c : madeComms) {
            if (!c.isPaid())
                total += c.getPrice();
        }

        return total;
    }

    public long getPaid() {
        long total = 0;
        for (Communication c : madeComms) {
            if (c.isPaid())
                total += c.getPrice();
        }

        return total;
    }

    public int getCommCount() {
        return receivedComms.size() + madeComms.size();
    }

    @Override
    public String toString() {
        String s = new StringJoiner("|")
                .add(this.key)
                .add(this.owner.getKey())
                .add(this.state.toString())
                .add(Long.toString(this.getPaid()))
                .add(Long.toString(this.getOwed()))
                .toString();

        if (friends.size() != 0) {
            s += "|";
            for (String f : friends)
                s += f + ",";

            // remove last comma
            s = s.substring(0, s.length() - 1);
        }

        return s;
    }
}
