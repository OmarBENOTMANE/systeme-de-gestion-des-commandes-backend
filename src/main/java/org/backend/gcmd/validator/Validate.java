package org.backend.gcmd.validator;

import org.backend.gcmd.exceptions.technical.IllegalNullParamException;

public class Validate {

    public static void notNull(Object obj, String message) {
        if (null == obj)
            throw new IllegalNullParamException(message);
    }

    public static void isTrue(final boolean expression, final String message) {
        if (!expression) {
            throw new IllegalNullParamException(message);
        }
    }

}
