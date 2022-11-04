package prr.app.lookups;

import prr.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.terminals.Terminal;
import prr.app.visitors.Renderer;
import prr.visitors.Selector;

/**
 * Show unused terminals (without communications).
 */
class DoShowUnusedTerminals extends Command<Network> {

    DoShowUnusedTerminals(Network receiver) {
        super(Label.SHOW_UNUSED_TERMINALS, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        Renderer renderer = new Renderer();
        Selector<Terminal> selector = new Selector<>() {
            @Override
            public boolean ok(Terminal terminal) {
                return terminal.getCommunicationCount() == 0;
            };
        };
        _receiver.acceptTerminalPrinter(selector, renderer);
        _display.popup(renderer.render());
    }
}
