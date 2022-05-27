package org.backend.gcmd.exceptions.technical;

public class InvalideKeyException extends TechnicalException {

    /**
     *
     */
    private static final long serialVersionUID = -4893638333439694227L;

    public InvalideKeyException(String message, Throwable cause) {
        super("Invalid key : ".concat(message), cause);
    }

    public InvalideKeyException(String message) {
        super("Invalid key :  ".concat(message));
    }

}
