package prr.terminals.states;

import prr.terminals.Terminal;

public class Off extends TerminalState {

    public Off(Terminal terminal) {
        super(terminal);
    }

    @Override
    public String toString() {
        return "OFF";
    }

    @Override
    public void toSilence() {
        terminal.setState(terminal.getSilenceState());
    }

    @Override
    public void toBusy() {
        // TODO Can't go to busy state
    }

    @Override
    public void toOff() {
        // TODO Already off
    }

    @Override
    public void toIdle() {
        terminal.setState(terminal.getIdleState());
    }
}
