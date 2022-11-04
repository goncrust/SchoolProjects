package prr.app.main;

import prr.NetworkManager;
import prr.app.exceptions.FileOpenFailedException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import java.io.IOException;
import prr.exceptions.MissingFileAssociationException;

/**
 * Command to save a file.
 */
class DoSaveFile extends Command<NetworkManager> {

    DoSaveFile(NetworkManager receiver) {
        super(Label.SAVE_FILE, receiver);
    }

    @Override
    protected final void execute() {
        if (!_receiver.getNetwork().isUnsaved())
            return;

        // If we changed the unsaved state after saving, the Network would always load
        // with the unsaved state on
        _receiver.getNetwork().saved();
        try {
            try {
                _receiver.save();
            } catch (MissingFileAssociationException e1) {
                try {
                    _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
                } catch (MissingFileAssociationException e2) {
                    // Empty string as filename
                    _receiver.getNetwork().failedSave();
                }
            }
        } catch (IOException e) {
            _receiver.getNetwork().failedSave();
        }
    }
}
