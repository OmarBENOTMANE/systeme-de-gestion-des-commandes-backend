package org.backend.gcmd.exceptions.business;

public class InvalidArgumentException extends BusinessException {
    private static final long serialVersionUID = 7830064295968918115L;


    public InvalidArgumentException(String message) {
        super(message);
    }

    public InvalidArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

}
