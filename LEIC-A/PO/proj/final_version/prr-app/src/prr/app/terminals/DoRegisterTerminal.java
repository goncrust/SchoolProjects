package prr.app.terminals;

import prr.Network;
import prr.app.exceptions.DuplicateTerminalKeyException;
import prr.app.exceptions.InvalidTerminalKeyException;
import prr.app.exceptions.UnknownClientKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import prr.exceptions.ClientDoesntExistException;
import prr.exceptions.IncorrectTerminalKeyException;
import prr.exceptions.TerminalExistsException;
import prr.exceptions.UnrecognizedTerminalTypeException;

/**
 * Register terminal.
 */
class DoRegisterTerminal extends Command<Network> {

    DoRegisterTerminal(Network receiver) {
        super(Label.REGISTER_TERMINAL, receiver);
        // command fields
        addStringField("key", Prompt.terminalKey());
        addOptionField("type", Prompt.terminalType(), new String[]{"BASIC", "FANCY"});
        addStringField("clientKey", Prompt.clientKey());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.registerTerminal(stringField("key"), stringField("type"), stringField("clientKey"));
        } catch (TerminalExistsException e) {
            throw new DuplicateTerminalKeyException(e.getKey());
        } catch (ClientDoesntExistException e) {
            throw new UnknownClientKeyException(e.getKey());
        } catch (IncorrectTerminalKeyException e) {
            throw new InvalidTerminalKeyException(e.getKey());
        } catch (UnrecognizedTerminalTypeException e) {
            e.printStackTrace();
        }
    }
}
