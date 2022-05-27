package org.backend.gcmd.exceptions.technical;

import org.backend.gcmd.exceptions.business.BusinessException;

public class ObjectNotFoundException extends BusinessException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}