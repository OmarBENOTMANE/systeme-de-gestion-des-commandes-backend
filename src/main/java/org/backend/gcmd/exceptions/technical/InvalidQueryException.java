package org.backend.gcmd.exceptions.technical;

public class InvalidQueryException extends TechnicalException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidQueryException() {
        super("Query must not be null");
    }

    public InvalidQueryException(String message) {
        super(message);
    }

    public InvalidQueryException(String message, Throwable cause) {
        super(message, cause);
    }
}