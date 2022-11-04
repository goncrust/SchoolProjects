package prr.terminals.states;

import java.io.Serial;
import java.io.Serializable;
import prr.terminals.Terminal;

public abstract class TerminalState implements Serializable {

    @Serial
    private static final long serialVersionUID = 202217101700L;

    protected final Terminal terminal;

    public TerminalState(Terminal terminal) {
        this.terminal = terminal;
    }

    public abstract void toSilence();

    public abstract void toBusy();

    public abstract void toOff();

    public abstract void toIdle();

}
