package prr.app.terminals;

import prr.Network;
import prr.exceptions.TerminalDoesntExistException;
import prr.app.exceptions.UnknownTerminalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Open a specific terminal's menu.
 */
class DoOpenMenuTerminalConsole extends Command<Network> {

    DoOpenMenuTerminalConsole(Network receiver) {
        super(Label.OPEN_MENU_TERMINAL, receiver);
        // command fields
        addStringField("key", Prompt.terminalKey());
    }

    @Override
    protected final void execute() throws CommandException {
        // create an instance of prr.app.terminal.Menu with the
        // selected Terminal
        try {
            (new prr.app.terminal.Menu(_receiver, _receiver.getTerminal(stringField("key")))).open();
        } catch(TerminalDoesntExistException e) {
            throw new UnknownTerminalKeyException(e.getKey());
        }
    }
}
