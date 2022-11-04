package prr.terminals.states;

import prr.exceptions.BusyTerminalException;
import prr.exceptions.OffTerminalException;
import prr.exceptions.SilencedTerminalException;
import prr.notifications.OffToIdleNotification;
import prr.notifications.OffToSilentNotification;
import prr.terminals.Terminal;

public class Off extends TerminalState {

    public Off(Terminal terminal) {
        super(terminal);
    }

    @Override
    public void toSilence() {
        terminal.notifyTextCommunicationObservers(new OffToSilentNotification(terminal.getKey()));
        terminal.setState(terminal.getSilenceState());
    }

    @Override
    public void toOff() throws OffTerminalException {
        throw new OffTerminalException(terminal.getKey());
    }

    @Override
    public void toOn() {
        terminal.notifyAllObservers(new OffToIdleNotification(terminal.getKey()));
        terminal.setState(terminal.getIdleState());
    }

    @Override
    public void receiveCommunication() throws BusyTerminalException, OffTerminalException, SilencedTerminalException {
        throw new OffTerminalException(terminal.getKey());
    }

    @Override
    public void startCommunication() throws OffTerminalException {
        throw new OffTerminalException(terminal.getKey());
    }

    @Override
    public void endCommunication() {
        // empty
    }

    @Override
    public String getLabel() {
        return "OFF";
    }

}
