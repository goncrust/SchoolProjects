package prr.app.lookups;

import prr.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.app.visitors.Renderer;
import prr.visitors.Selector;
import prr.clients.Client;

/**
 * Show clients with negative balance.
 */
class DoShowClientsWithDebts extends Command<Network> {

    DoShowClientsWithDebts(Network receiver) {
        super(Label.SHOW_CLIENTS_WITH_DEBTS, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        Renderer renderer = new Renderer();
        Selector<Client> selector = new Selector<>() {
            @Override
            public boolean ok(Client client) {
                return client.getDebt() > 0;
            };
        };
        _receiver.acceptSimpleClientPrinter(selector, renderer);
        _display.popup(renderer.render());
    }
}
