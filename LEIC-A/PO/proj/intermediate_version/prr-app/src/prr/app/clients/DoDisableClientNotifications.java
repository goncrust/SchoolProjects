package prr.app.clients;

import prr.Network;
import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Disable client notifications.
 */
class DoDisableClientNotifications extends Command<Network> {

    DoDisableClientNotifications(Network receiver) {
        super(Label.DISABLE_CLIENT_NOTIFICATIONS, receiver);
        // FIXME add command fields
    }

    @Override
    protected final void execute() throws CommandException {
        // FIXME implement command
    }
}
