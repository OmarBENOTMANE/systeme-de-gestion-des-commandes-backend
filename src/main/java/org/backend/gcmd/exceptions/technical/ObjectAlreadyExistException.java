package org.backend.gcmd.exceptions.technical;

import org.backend.gcmd.exceptions.business.BusinessException;

public class ObjectAlreadyExistException extends BusinessException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ObjectAlreadyExistException(String message) {
        super(message);
    }

    public ObjectAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}