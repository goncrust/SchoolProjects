package prr.app.terminals;

import prr.Network;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.app.visitors.Renderer;
import prr.visitors.Selector;

/**
 * Show all terminals.
 */
class DoShowAllTerminals extends Command<Network> {

    DoShowAllTerminals(Network receiver) {
        super(Label.SHOW_ALL_TERMINALS, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        Renderer renderer = new Renderer();
        Selector<Terminal> selector = new Selector<>() {
        };
        _receiver.acceptTerminalPrinter(selector, renderer);
        _display.addAll(renderer.render());
        _display.display();
    }
}
