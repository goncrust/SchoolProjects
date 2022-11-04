package prr.app.main;

import prr.NetworkManager;
import prr.exceptions.UnavailableFileException;
import prr.app.exceptions.FileOpenFailedException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//Add more imports if needed

/**
 * Command to open a file.
 */
class DoOpenFile extends Command<NetworkManager> {

    DoOpenFile(NetworkManager receiver) {
        super(Label.OPEN_FILE, receiver);
        addStringField("file", Prompt.openFile());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.load(stringField("file"));
        } catch (UnavailableFileException e) {
            throw new FileOpenFailedException(e);
        }
    }
}
