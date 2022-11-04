package prr.app.main;

import prr.Network;
import prr.NetworkManager;
import pt.tecnico.uilib.menus.Command;

/**
 * Command for opening the menu for terminal management.
 */
class DoOpenMenuTerminals extends Command<NetworkManager> {

    DoOpenMenuTerminals(NetworkManager receiver) {
        super(Label.OPEN_MENU_TERMINALS, receiver);
    }

    @Override
    protected final void execute() {
        (new prr.app.terminals.Menu(_receiver.getNetwork())).open();
    }
}
