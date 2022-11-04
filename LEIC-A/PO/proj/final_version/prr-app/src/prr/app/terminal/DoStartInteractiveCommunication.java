package prr.app.terminal;

import prr.Network;
import prr.app.exceptions.UnknownTerminalKeyException;
import prr.exceptions.OffTerminalException;
import prr.exceptions.SilencedTerminalException;
import prr.exceptions.BusyTerminalException;
import prr.exceptions.InvalidDestinationException;
import prr.exceptions.InvalidOriginException;
import prr.exceptions.TerminalDoesntExistException;
import prr.terminals.Terminal;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for starting communication.
 */
class DoStartInteractiveCommunication extends TerminalCommand {

    DoStartInteractiveCommunication(Network context, Terminal terminal) {
        super(Label.START_INTERACTIVE_COMMUNICATION, context, terminal, receiver -> receiver.canStartCommunication());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.startInteractiveCommunication(Form.requestString(Prompt.terminalKey()),
                    Form.requestOption(Prompt.commType(), new String[] { "VOICE", "VIDEO" }), _network);
        } catch (TerminalDoesntExistException e) {
            throw new UnknownTerminalKeyException(e.getKey());
        } catch (InvalidOriginException e) {
            _display.popup(Message.unsupportedAtOrigin(e.getKey(), e.getType()));
        } catch (InvalidDestinationException e) {
            _display.popup(Message.unsupportedAtDestination(e.getKey(), e.getType()));
        } catch (OffTerminalException e) {
            _display.popup(Message.destinationIsOff(e.getKey()));
        } catch (SilencedTerminalException e) {
            _display.popup(Message.destinationIsSilent(e.getKey()));
        } catch (BusyTerminalException e) {
            _display.popup(Message.destinationIsBusy(e.getKey()));
        }
    }
}
