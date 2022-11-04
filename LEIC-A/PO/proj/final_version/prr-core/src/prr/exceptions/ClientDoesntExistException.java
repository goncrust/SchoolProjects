package prr.exceptions;

import java.io.Serial;
/**
 * Exception thrown when trying to find a client with
 * a key that hasn't been registered.
 */
public class ClientDoesntExistException extends KeyRelatedException {
    
    @Serial
    private static final long serialVersionUID = 202217101700L;
    
    /** @param key the nonexistent key */
    public ClientDoesntExistException(String key) {
        super(key);
    }
}
