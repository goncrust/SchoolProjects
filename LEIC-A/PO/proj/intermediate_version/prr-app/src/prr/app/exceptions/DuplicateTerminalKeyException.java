package prr.app.exceptions;

import pt.tecnico.uilib.menus.CommandException;

/** Exception thrown when a terminal key is duplicated. */
public class DuplicateTerminalKeyException extends CommandException {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    /** @param key Duplicate terminal to report. */
    public DuplicateTerminalKeyException(String key) {
        super(Message.duplicateTerminalKey(key));
    }

}
