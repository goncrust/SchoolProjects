package prr.app.terminal;

import prr.Network;
import prr.exceptions.BusyTerminalException;
import prr.exceptions.OffTerminalException;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Turn off the terminal.
 */
class DoTurnOffTerminal extends TerminalCommand {

    DoTurnOffTerminal(Network context, Terminal terminal) {
        super(Label.POWER_OFF, context, terminal);
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.toOff();
        } catch (OffTerminalException e) {
            _display.popup(Message.alreadyOff());
        } catch (BusyTerminalException e) {
            // empty
        }
    }
}
