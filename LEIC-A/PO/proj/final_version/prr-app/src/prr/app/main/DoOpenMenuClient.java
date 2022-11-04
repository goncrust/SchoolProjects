package prr.app.main;

import prr.Network;
import prr.NetworkManager;
import pt.tecnico.uilib.menus.Command;

/**
 * Command for opening the menu for clients.
 */
class DoOpenMenuClient extends Command<NetworkManager> {

    DoOpenMenuClient(NetworkManager receiver) {
        super(Label.OPEN_MENU_CLIENTS, receiver);
    }

    @Override
    protected final void execute() {
        (new prr.app.clients.Menu(_receiver.getNetwork())).open();
    }
}
