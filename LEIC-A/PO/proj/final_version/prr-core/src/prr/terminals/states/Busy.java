package prr.terminals.states;

import prr.exceptions.BusyTerminalException;
import prr.notifications.BusyToIdleNotification;
import prr.terminals.Terminal;

public class Busy extends TerminalState {

    public Busy(Terminal terminal) {
        super(terminal);
    }

    @Override
    public void toSilence() throws BusyTerminalException {
        throw new BusyTerminalException(terminal.getKey());
    }

    @Override
    public void toOff() throws BusyTerminalException {
        throw new BusyTerminalException(terminal.getKey());
    }

    @Override
    public void toOn() throws BusyTerminalException {
        throw new BusyTerminalException(terminal.getKey());
    }

    @Override
    public void startCommunication() throws BusyTerminalException {
        throw new BusyTerminalException(terminal.getKey());
    }

    @Override
    public void receiveCommunication() throws BusyTerminalException {
        throw new BusyTerminalException(terminal.getKey());
    }

    @Override
    public void endCommunication() {
        if ((terminal.getSavedState()).equals(terminal.getIdleState()))
            terminal.notifyInteractiveCommunicationObservers(new BusyToIdleNotification(terminal.getKey()));
        terminal.setState(terminal.getSavedState());
    }

    @Override
    public String getLabel() {
        return "BUSY";
    }

}
