package prr.terminals.states;

import prr.exceptions.BusyTerminalException;
import prr.exceptions.IdleTerminalException;
import prr.exceptions.OffTerminalException;
import prr.exceptions.SilencedTerminalException;
import prr.terminals.Terminal;

public class Idle extends TerminalState {

    public Idle(Terminal terminal) {
        super(terminal);
    }

    @Override
    public void toSilence() {
        terminal.setState(terminal.getSilenceState());
    }

    @Override
    public void toOff() {
        terminal.setState(terminal.getOffState());
    }

    @Override
    public void toOn() throws IdleTerminalException {
        throw new IdleTerminalException(terminal.getKey());
    }

    @Override
    public void startCommunication() {
        terminal.saveState(this);
        terminal.setState(terminal.getBusyState());
    }

    @Override
    public void receiveCommunication() throws BusyTerminalException, OffTerminalException, SilencedTerminalException {
        terminal.saveState(this);
        terminal.setState(terminal.getBusyState());
    }

    @Override
    public void endCommunication() {
        // empty
    }

    @Override
    public String getLabel() {
        return "IDLE";
    }

}
