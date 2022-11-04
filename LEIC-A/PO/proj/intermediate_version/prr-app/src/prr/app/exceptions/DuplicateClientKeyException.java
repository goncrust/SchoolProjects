package prr.app.exceptions;

import pt.tecnico.uilib.menus.CommandException;

/** Exception thrown when a client key is duplicated. */
public class DuplicateClientKeyException extends CommandException {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    /** @param key the duplicated key */
    public DuplicateClientKeyException(String key) {
        super(Message.duplicateClientKey(key));
    }

}
