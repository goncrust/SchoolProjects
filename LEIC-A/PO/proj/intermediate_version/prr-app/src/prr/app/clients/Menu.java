package prr.app.clients;

import prr.Network;

/**
 * Menu for client operations.
 */
public final class Menu extends pt.tecnico.uilib.menus.Menu {

    public Menu(Network receiver) {
        super(Label.TITLE, //
                new DoShowClient(receiver), //
                new DoShowAllClients(receiver), //
                new DoRegisterClient(receiver), //
                new DoEnableClientNotifications(receiver), //
                new DoDisableClientNotifications(receiver), //
                new DoShowClientPaymentsAndDebts(receiver));
    }
}
