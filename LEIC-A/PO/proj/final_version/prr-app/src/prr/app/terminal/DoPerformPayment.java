package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;
import prr.exceptions.InvalidCommunicationException;

/**
 * Perform payment.
 */
class DoPerformPayment extends TerminalCommand {

    DoPerformPayment(Network context, Terminal terminal) {
        super(Label.PERFORM_PAYMENT, context, terminal);
        addIntegerField("key", Prompt.commKey());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.payCommunication(integerField("key"));
        } catch (InvalidCommunicationException e) {
            _display.popup(Message.invalidCommunication());
        }
    }
}
