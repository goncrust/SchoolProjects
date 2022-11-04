package prr.app.lookups;

import prr.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show terminals with positive balance.
 */
class DoShowTerminalsWithPositiveBalance extends Command<Network> {

    DoShowTerminalsWithPositiveBalance(Network receiver) {
        super(Label.SHOW_TERMINALS_WITH_POSITIVE_BALANCE, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        // FIXME implement command
    }
}
