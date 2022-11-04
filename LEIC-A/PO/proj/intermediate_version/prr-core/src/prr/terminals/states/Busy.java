package prr.terminals.states;

import prr.terminals.Terminal;

public class Busy extends TerminalState {

    public Busy(Terminal terminal) {
        super(terminal);
    }

    @Override
    public String toString() {
        return "BUSY";
    }

    @Override
    public void toSilence() {
        terminal.setState(terminal.getSilenceState());
    }

    @Override
    public void toBusy() {
        // TODO Already in busy state
    }

    @Override
    public void toOff() {
        // TODO Can't go to off state
    }

    @Override
    public void toIdle() {
        terminal.setState(terminal.getIdleState());
    }
}
