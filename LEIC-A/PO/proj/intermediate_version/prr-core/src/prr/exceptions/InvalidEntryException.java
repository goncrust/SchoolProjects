package prr.exceptions;

import java.io.Serial;
/**
 * Exception for invalid import file entries.
 */
public class InvalidEntryException extends Exception {

    /** Class serial number. */
    @Serial
    private static final long serialVersionUID = 202208091753L;

    /** Invalid entry specification. */
    private String entrySpecification;

    /**
     * @param entrySpecification
     */
    public InvalidEntryException(String entrySpecification) {
        this.entrySpecification = entrySpecification;
    }

    /**
     * @param entrySpecification
     * @param cause
     */
    public InvalidEntryException(String entrySpecification, Exception cause) {
        super(cause);
        this.entrySpecification = entrySpecification;
    }

    /**
     * @return the invalid entry specification.
     */
    public String getEntrySpecification() {
        return entrySpecification;
    }
}
