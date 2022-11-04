package prr.app.exceptions;

import pt.tecnico.uilib.menus.CommandException;

/** Exception for unknown terminals. */
public class UnknownTerminalKeyException extends CommandException {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    /** @param key Unknown terminal to report. */
    public UnknownTerminalKeyException(String key) {
        super(Message.unknownTerminalKey(key));
    }

}
