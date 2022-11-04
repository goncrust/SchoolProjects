package prr.app.exceptions;

import pt.tecnico.uilib.menus.CommandException;

/**
 * Exception for unknown clients.
 */
public class UnknownClientKeyException extends CommandException {

    /** Serial number (serialization) */
    private static final long serialVersionUID = 202208091753L;

    /** @param key Unknown client to report. */
    public UnknownClientKeyException(String key) {
        super(Message.unknownClientKey(key));
    }

}
