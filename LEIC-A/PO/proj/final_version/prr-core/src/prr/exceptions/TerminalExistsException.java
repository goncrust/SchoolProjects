package prr.exceptions;

import java.io.Serial;
/**
 * Exception thrown when trying to find a client with
 * a key that hasn't been registered.
 */
public class TerminalExistsException extends KeyRelatedException {
    
    @Serial
    private static final long serialVersionUID = 202217101700L;
    
    /** @param key the repeated key */
    public TerminalExistsException(String key) {
        super(key);
    }
}
