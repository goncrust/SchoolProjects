package prr.exceptions;

import java.io.Serial;

public class IdleTerminalException extends TerminalStateException {

    @Serial
    private static final long serialVersionUID = 202230100402L;

    /** @param key the invalid key */
    public IdleTerminalException(String key) {
        super(key);
    }
}