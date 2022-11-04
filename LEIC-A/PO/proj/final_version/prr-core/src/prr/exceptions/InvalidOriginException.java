package prr.exceptions;

import java.io.Serial;

public class InvalidOriginException extends InvalidOperationException {

    @Serial
    private static final long serialVersionUID = 202231101609L;

    public InvalidOriginException(String key, String type) {
        super(key, type);
    }
}