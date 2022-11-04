package prr.app.lookups;

import prr.Network;
import prr.clients.Client;
import prr.app.visitors.Renderer;
import prr.visitors.Selector;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Show clients with positive balance.
 */
class DoShowClientsWithoutDebts extends Command<Network> {

    DoShowClientsWithoutDebts(Network receiver) {
        super(Label.SHOW_CLIENTS_WITHOUT_DEBTS, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        Renderer renderer = new Renderer();
        Selector<Client> selector = new Selector<>() {
            @Override
            public boolean ok(Client client) {
                return client.getDebt() == 0;
            };
        };
        _receiver.acceptSimpleClientPrinter(selector, renderer);
        _display.popup(renderer.render());
    }
}
