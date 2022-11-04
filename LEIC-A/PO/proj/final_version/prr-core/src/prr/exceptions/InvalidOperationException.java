package prr.exceptions;

public abstract class InvalidOperationException extends Exception {

    private final String key;
    private final String type;

    /**
     * @param key
     * @param type
     */
    public InvalidOperationException(String key, String type) {
        this.key = key;
        this.type = type;
    }

    /** @return The key of the terminal that caused the error */
    public String getKey() {
        return this.key;
    }

    /** @return The type of the operation that caused the error */
    public String getType() {
        return this.type;
    }
}
