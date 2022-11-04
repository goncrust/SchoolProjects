package prr.app.clients;

import prr.Network;
import prr.app.exceptions.UnknownClientKeyException;
import prr.exceptions.ClientDoesntExistException;
import prr.exceptions.NotificationsAlreadyEnabledException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Enable client notifications.
 */
class DoEnableClientNotifications extends Command<Network> {

    DoEnableClientNotifications(Network receiver) {
        super(Label.ENABLE_CLIENT_NOTIFICATIONS, receiver);
        addStringField("key", Prompt.key());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.enableNotifications(stringField("key"));
        } catch (ClientDoesntExistException e) {
            throw new UnknownClientKeyException(e.getKey());
        } catch (NotificationsAlreadyEnabledException e) {
            _display.popup(Message.clientNotificationsAlreadyEnabled());
        }
    }
}
