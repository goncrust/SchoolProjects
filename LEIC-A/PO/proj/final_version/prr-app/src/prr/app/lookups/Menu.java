package prr.app.lookups;

import prr.Network;

/**
 * Menu for lookup operations.
 */
public final class Menu extends pt.tecnico.uilib.menus.Menu {

    public Menu(Network receiver) {
        super(Label.TITLE, //
                new DoShowAllCommunications(receiver), //
                new DoShowCommunicationsFromClient(receiver), //
                new DoShowCommunicationsToClient(receiver), //
                new DoShowClientsWithoutDebts(receiver), //
                new DoShowClientsWithDebts(receiver), //
                new DoShowUnusedTerminals(receiver), //
                new DoShowTerminalsWithPositiveBalance(receiver)//
        );
    }
}
