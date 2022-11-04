package prr.app.terminal;

import prr.Network;
import prr.exceptions.BusyTerminalException;
import prr.exceptions.SilencedTerminalException;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Silence the terminal.
 */
class DoSilenceTerminal extends TerminalCommand {

    DoSilenceTerminal(Network context, Terminal terminal) {
        super(Label.MUTE_TERMINAL, context, terminal);
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.toSilence();
        } catch (SilencedTerminalException e) {
            _display.popup(Message.alreadySilent());
        } catch (BusyTerminalException e) {
            // Silent failure
        }
    }
}
