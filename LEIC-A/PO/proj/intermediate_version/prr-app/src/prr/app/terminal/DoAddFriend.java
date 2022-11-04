package prr.app.terminal;

import prr.Network;
import prr.terminals.Terminal;
import pt.tecnico.uilib.menus.CommandException;
//FIXME add more imports if needed

/**
 * Add a friend.
 */
class DoAddFriend extends TerminalCommand {

    DoAddFriend(Network context, Terminal terminal) {
        super(Label.ADD_FRIEND, context, terminal);
        // FIXME add command fields
    }

    @Override
    protected final void execute() throws CommandException {
        // FIXME implement command
    }
}
