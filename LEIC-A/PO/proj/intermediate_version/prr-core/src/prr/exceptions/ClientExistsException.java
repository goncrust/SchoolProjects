package prr.exceptions;

import java.io.Serial;
/**
 * Exception thrown when trying to register a client with
 * a key that already exists.
 */
public class ClientExistsException extends KeyRelatedException {
    
    @Serial
    private static final long serialVersionUID = 202217101700L;
    
    /** @param key the duplicated key */
    public ClientExistsException(String key) {
        super(key);
    }
}
