package prr.exceptions;

import java.io.Serial;

public class OffTerminalException extends TerminalStateException {

    @Serial
    private static final long serialVersionUID = 202221102338L;

    /** @param key the invalid key */
    public OffTerminalException(String key) {
        super(key);
    }
}