package prr.app.main;

import prr.Network;
import prr.NetworkManager;
import pt.tecnico.uilib.menus.Command;

/**
 * Command for opening the menu for lookups.
 */
class DoOpenMenuLookups extends Command<NetworkManager> {

    DoOpenMenuLookups(NetworkManager receiver) {
        super(Label.OPEN_MENU_LOOKUPS, receiver);
    }

    @Override
    protected final void execute() {
        (new prr.app.lookups.Menu(_receiver.getNetwork())).open();
    }
}
