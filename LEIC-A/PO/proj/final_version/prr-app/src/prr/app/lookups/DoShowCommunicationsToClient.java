package prr.app.lookups;

import prr.Network;
import prr.clients.Client;
import prr.app.visitors.Renderer;
import prr.visitors.Selector;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.exceptions.ClientDoesntExistException;
import prr.app.exceptions.UnknownClientKeyException;

/**
 * Show communications to a client.
 */
class DoShowCommunicationsToClient extends Command<Network> {

    DoShowCommunicationsToClient(Network receiver) {
        super(Label.SHOW_COMMUNICATIONS_TO_CLIENT, receiver);
        addStringField("client", Prompt.clientKey());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            String key = stringField("key");
            Renderer renderer = new Renderer();
            Selector<Client> selector = new Selector<>() {
                public boolean ok(Client c) {
                    return (c.getKey()).equals(key);
                };
            };
            _receiver.checkClient(key);
            _receiver.acceptReceivedCommunicationsPrinter(selector, renderer);
            _display.popup(renderer.render());
        } catch (ClientDoesntExistException e) {
            throw new UnknownClientKeyException(e.getKey());
        }
    }
}