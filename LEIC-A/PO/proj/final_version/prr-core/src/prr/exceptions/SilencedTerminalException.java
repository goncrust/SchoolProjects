package prr.exceptions;

import java.io.Serial;

public class SilencedTerminalException extends TerminalStateException {

    @Serial
    private static final long serialVersionUID = 202221102338L;

    /** @param key the invalid key */
    public SilencedTerminalException(String key) {
        super(key);
    }
}