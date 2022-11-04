package prr.terminals.states;

import prr.terminals.Terminal;

public class Silence extends TerminalState {

    public Silence(Terminal terminal) {
        super(terminal);
    }

    @Override
    public String toString() {
        return "SILENCE";
    }

    @Override
    public void toSilence() {
        // TODO Already in silence
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
        terminal.setState(terminal.getIdleState());
    }
}
