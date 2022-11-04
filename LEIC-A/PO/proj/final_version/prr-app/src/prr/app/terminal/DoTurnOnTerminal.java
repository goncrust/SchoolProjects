package prr.app.terminal;

import prr.Network;
import prr.exceptions.BusyTerminalException;
import prr.exceptions.IdleTerminalException;
import prr.exceptions.SilencedTerminalException;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Turn on the terminal.
 */
class DoTurnOnTerminal extends TerminalCommand {

    DoTurnOnTerminal(Network context, Terminal terminal) {
        super(Label.POWER_ON, context, terminal);
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.toOn();
        } catch (IdleTerminalException e) {
            _display.popup(Message.alreadyOn());
        } catch (BusyTerminalException | SilencedTerminalException e) {
            // empty
        }
    }
}
