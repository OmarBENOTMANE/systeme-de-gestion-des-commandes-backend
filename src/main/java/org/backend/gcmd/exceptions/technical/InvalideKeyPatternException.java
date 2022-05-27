package org.backend.gcmd.exceptions.technical;

public class InvalideKeyPatternException extends TechnicalException {

    /**
     *
     */
    private static final long serialVersionUID = -4893638333439694227L;

    public InvalideKeyPatternException(String message, Throwable cause) {
        super("Invalid key pattern: ".concat(message), cause);
    }

    public InvalideKeyPatternException(String message) {
        super("Invalid key pattern:  ".concat(message));
    }

}
