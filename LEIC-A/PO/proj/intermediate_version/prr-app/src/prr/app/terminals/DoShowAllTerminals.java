package prr.app.terminals;

import prr.Network;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.Collection;

/**
 * Show all terminals.
 */
class DoShowAllTerminals extends Command<Network> {

    DoShowAllTerminals(Network receiver) {
        super(Label.SHOW_ALL_TERMINALS, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        Collection<Terminal> terminals = _receiver.getAllTerminals();
        for (Terminal t : terminals)
            _display.popup(t.toString());
    }
}
