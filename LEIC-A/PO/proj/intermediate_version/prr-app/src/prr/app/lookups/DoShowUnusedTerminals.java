package prr.app.lookups;

import prr.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.terminals.Terminal;
import java.util.Collection;

/**
 * Show unused terminals (without communications).
 */
class DoShowUnusedTerminals extends Command<Network> {

    DoShowUnusedTerminals(Network receiver) {
        super(Label.SHOW_UNUSED_TERMINALS, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        Collection<Terminal> terminals = _receiver.getUnusedTerminals();
        for (Terminal t : terminals) {
            _display.popup(t.toString());
        }
    }
}
