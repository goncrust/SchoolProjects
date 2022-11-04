package prr.exceptions;

import java.io.Serial;

public class InvalidDestinationException extends InvalidOperationException {

    @Serial
    private static final long serialVersionUID = 202231101610L;
    public InvalidDestinationException(String key, String type) {
        super(key, type);
    }
}