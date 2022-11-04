package prr.exceptions;

import java.io.Serial;

/**
 * Exception for unknown terminal types
 */
public class UnrecognizedTerminalTypeException extends Exception {

    @Serial
    private static final long serialVersionUID = 202218101154L;

    private final String type;

    /** @param type */
    public UnrecognizedTerminalTypeException(String type) {
        this.type = type;
    }

    /** @return The type that caused the error */
    public String getType() {
        return this.type;
    }
}
