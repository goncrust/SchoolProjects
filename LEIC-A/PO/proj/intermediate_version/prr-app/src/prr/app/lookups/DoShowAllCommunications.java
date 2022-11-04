package prr.app.lookups;

import prr.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME more imports if needed

/**
 * Command for showing all communications.
 */
class DoShowAllCommunications extends Command<Network> {

    DoShowAllCommunications(Network receiver) {
        super(Label.SHOW_ALL_COMMUNICATIONS, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        // FIXME implement command
    }
}
