package org.backend.gcmd.exceptions.technical;

public class MissingIdException extends TechnicalException {
    private static final long serialVersionUID = -3377325407569060574L;


    public MissingIdException(String message) {
        super("ID_IS_MISSING" + message);

    }

}
