package prr.app.clients;

import prr.Network;
import prr.app.exceptions.UnknownClientKeyException;
import prr.exceptions.ClientDoesntExistException;
import prr.exceptions.NotificationsAlreadyDisabledException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Disable client notifications.
 */
class DoDisableClientNotifications extends Command<Network> {

    DoDisableClientNotifications(Network receiver) {
        super(Label.DISABLE_CLIENT_NOTIFICATIONS, receiver);
        addStringField("key", Prompt.key());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.disableNotifications(stringField("key"));
        } catch (ClientDoesntExistException e) {
            throw new UnknownClientKeyException(e.getKey());
        } catch (NotificationsAlreadyDisabledException e) {
            _display.popup(Message.clientNotificationsAlreadyDisabled());
        }
    }
}
