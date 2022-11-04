package prr.app.terminal;

import prr.Network;
import prr.app.visitors.Renderer;
import prr.exceptions.NoOngoingCommunicationException;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for showing the ongoing communication.
 */
class DoShowOngoingCommunication extends TerminalCommand {

    DoShowOngoingCommunication(Network context, Terminal terminal) {
        super(Label.SHOW_ONGOING_COMMUNICATION, context, terminal);
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            Renderer renderer = new Renderer();
            _receiver.acceptCurrentCommunicationPrinter(renderer);
            _display.popup(renderer.render());
        } catch (NoOngoingCommunicationException e) {
            _display.popup(Message.noOngoingCommunication());
        }
    }
}
