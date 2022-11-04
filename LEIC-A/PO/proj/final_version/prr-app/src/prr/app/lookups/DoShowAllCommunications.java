package prr.app.lookups;

import prr.Network;
import prr.clients.Client;
import prr.app.visitors.Renderer;
import prr.visitors.Selector;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for showing all communications.
 */
class DoShowAllCommunications extends Command<Network> {

    DoShowAllCommunications(Network receiver) {
        super(Label.SHOW_ALL_COMMUNICATIONS, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        Renderer renderer = new Renderer();
        Selector<Client> selector = new Selector<>() {
        };
        _receiver.acceptMadeCommunicationsPrinter(selector, renderer);
        _display.popup(renderer.render());
    }
}
