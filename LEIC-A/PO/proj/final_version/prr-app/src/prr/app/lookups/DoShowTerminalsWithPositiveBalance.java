package prr.app.lookups;

import prr.Network;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.app.visitors.Renderer;
import prr.visitors.Selector;
import prr.terminals.Terminal;

/**
 * Show terminals with positive balance.
 */
class DoShowTerminalsWithPositiveBalance extends Command<Network> {

    DoShowTerminalsWithPositiveBalance(Network receiver) {
        super(Label.SHOW_TERMINALS_WITH_POSITIVE_BALANCE, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        Renderer renderer = new Renderer();
        Selector<Terminal> selector = new Selector<>() {
            @Override
            public boolean ok(Terminal terminal) {
                return terminal.getBalance() > 0;
            };
        };
        _receiver.acceptTerminalPrinter(selector, renderer);
        _display.popup(renderer.render());
    }
}
