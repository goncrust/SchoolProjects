package prr.terminals.states;

import prr.terminals.Terminal;

public class Idle extends TerminalState {

    public Idle(Terminal terminal) {
        super(terminal);
    }

    @Override
    public String toString() {
        return "IDLE";
    }

    @Override
    public void toSilence() {
        terminal.setState(terminal.getSilenceState());
    }

    @Override
    public void toBusy() {
        terminal.setState(terminal.getBusyState());
    }

    @Override
    public void toOff() {
        terminal.setState(terminal.getOffState());
    }

    @Override
    public void toIdle() {
        // TODO Already in idle state
    }
}
