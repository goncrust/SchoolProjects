package prr.app.clients;

import prr.Network;
import prr.app.exceptions.UnknownClientKeyException;
import prr.exceptions.ClientDoesntExistException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.app.visitors.Renderer;
import prr.clients.Client;
import prr.visitors.Selector;

/**
 * Show specific client: also show previous notifications.
 */
class DoShowClient extends Command<Network> {

    DoShowClient(Network receiver) {
        super(Label.SHOW_CLIENT, receiver);
        addStringField("key", Prompt.key());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            String key = stringField("key");
            Renderer renderer = new Renderer();
            Selector<Client> selector = new Selector<>() {
                @Override
                public boolean ok(Client c) {
                    return (c.getKey()).equals(key);
                };
            };
            _receiver.checkClient(key);
            _receiver.acceptVerboseClientPrinter(selector, renderer);
            _display.popup(renderer.render());
        } catch (ClientDoesntExistException e) {
            throw new UnknownClientKeyException(e.getKey());
        }
    }
}
