package org.backend.gcmd.exceptions.technical;

public class IntegrityConstraintViolationException extends TechnicalException {
    private static final long serialVersionUID = -3377325407569060574L;

    public IntegrityConstraintViolationException(String message) {
        super("ENTITY_ALREADY_IN_USE: " + message);
    }

}
