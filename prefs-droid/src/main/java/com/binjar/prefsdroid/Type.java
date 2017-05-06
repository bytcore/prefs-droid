package com.binjar.prefsdroid;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Arif Islam on 06-May-17.
 */

@Retention(RetentionPolicy.SOURCE) @Target(ElementType.FIELD)
public @interface Type {
    enum ValueTpe{
        BOOLEAN, INTEGER, LONG, FLOAT, DOUBLE, STRING, STRING_SET, OBJECT
    }

    ValueTpe value();
}
