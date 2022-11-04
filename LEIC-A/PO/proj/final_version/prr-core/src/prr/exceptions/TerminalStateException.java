package prr.exceptions;

public abstract class TerminalStateException extends Exception {
    
    private final String key;

    /** @param key */
    public TerminalStateException(String key) {
        this.key = key;
    }

    /** @return The key of the terminal that caused the error */
    public String getKey() {
        return this.key;
    }
}
