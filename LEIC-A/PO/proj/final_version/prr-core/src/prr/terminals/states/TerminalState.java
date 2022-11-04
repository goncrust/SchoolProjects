package prr.terminals.states;

import java.io.Serial;
import java.io.Serializable;

import prr.exceptions.BusyTerminalException;
import prr.exceptions.IdleTerminalException;
import prr.exceptions.OffTerminalException;
import prr.exceptions.SilencedTerminalException;
import prr.terminals.Terminal;

public abstract class TerminalState implements Serializable {

    @Serial
    private static final long serialVersionUID = 202217101700L;

    protected final Terminal terminal;

    public TerminalState(Terminal terminal) {
        this.terminal = terminal;
    }

    public abstract void toSilence() throws SilencedTerminalException, BusyTerminalException;

    public abstract void toOff() throws BusyTerminalException, OffTerminalException;

    public abstract void toOn() throws IdleTerminalException, BusyTerminalException, SilencedTerminalException;

    public abstract void startCommunication() throws BusyTerminalException, OffTerminalException;

    public abstract void receiveCommunication()
            throws BusyTerminalException, OffTerminalException, SilencedTerminalException;

    public abstract void endCommunication();

    public abstract String getLabel();

}
