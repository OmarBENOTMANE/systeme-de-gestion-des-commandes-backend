package org.backend.gcmd.exceptions.technical;

public class IllegalNullParamException extends TechnicalException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public IllegalNullParamException(String message) {
        super(message);
    }

    public IllegalNullParamException(String message, Throwable cause) {
        super(message, cause);
    }
}