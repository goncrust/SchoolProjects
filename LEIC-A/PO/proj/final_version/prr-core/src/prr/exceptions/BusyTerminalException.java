package prr.exceptions;

import java.io.Serial;

public class BusyTerminalException extends TerminalStateException {

    @Serial
    private static final long serialVersionUID = 202221102338L;

    /** @param key the invalid key */
    public BusyTerminalException(String key) {
        super(key);
    }
}