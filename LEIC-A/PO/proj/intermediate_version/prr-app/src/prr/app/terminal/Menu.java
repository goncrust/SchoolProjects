package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;

/**
 * Terminal console menu.
 */
public final class Menu extends pt.tecnico.uilib.menus.Menu {

    public Menu(Network network, Terminal terminal) {
        super(Label.TITLE, //
                new DoTurnOnTerminal(network, terminal), //
                new DoSilenceTerminal(network, terminal), //
                new DoTurnOffTerminal(network, terminal), //
                new DoAddFriend(network, terminal), //
                new DoRemoveFriend(network, terminal), //
                new DoPerformPayment(network, terminal), //
                new DoShowTerminalBalance(network, terminal), //
                new DoSendTextCommunication(network, terminal),
                new DoStartInteractiveCommunication(network, terminal), //
                new DoEndInteractiveCommunication(network, terminal), //
                new DoShowOngoingCommunication(network, terminal)//
        );
    }
}
