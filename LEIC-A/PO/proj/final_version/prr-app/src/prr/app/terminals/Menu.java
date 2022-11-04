package prr.app.terminals;

import prr.Network;

/**
 * Menu for terminal operations.
 */
public class Menu extends pt.tecnico.uilib.menus.Menu {

    public Menu(Network receiver) {
        super(Label.TITLE, //
                new DoShowAllTerminals(receiver), //
                new DoRegisterTerminal(receiver), //
                new DoOpenMenuTerminalConsole(receiver)//
        );
    }
}
