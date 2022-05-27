package org.backend.gcmd.validator;

import org.backend.gcmd.exceptions.technical.IllegalNullParamException;

public class Validate {

    static public void notNull(Object obj, String message) {
        if (null == obj)
            throw new IllegalNullParamException(message);
    }

    static public void isTrue(final boolean expression, final String message) {
        if (!expression) {
            throw new IllegalNullParamException(message);
        }
    }

}
