package prr.app.clients;

import prr.Network;
import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Show the payments and debts of a client.
 */
class DoShowClientPaymentsAndDebts extends Command<Network> {

    DoShowClientPaymentsAndDebts(Network receiver) {
        super(Label.SHOW_CLIENT_BALANCE, receiver);
        // FIXME add command fields
    }

    @Override
    protected final void execute() throws CommandException {
        // FIXME implement command
    }
}
