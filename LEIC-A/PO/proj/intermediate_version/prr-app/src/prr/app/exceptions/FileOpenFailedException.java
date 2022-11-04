package prr.app.exceptions;

import pt.tecnico.uilib.menus.CommandException;

/** Exception for reporting general problems opening and processing files. */
public class FileOpenFailedException extends CommandException {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    /** @param e the cause of this exception. */
    public FileOpenFailedException(Exception e) {
        super(Message.problemOpeningFile(e), e);
    }

}
