package org.backend.gcmd.exceptions.technical;

public class FileNotFoundException extends TechnicalException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FileNotFoundException(String message) {
        super(message);
    }

    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}