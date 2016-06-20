package com.example.zzt.whyfi.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

/**
 * Created by zzt on 6/18/16.
 * <p/>
 * Usage:
 */
@Documented
@Retention(CLASS)
@Target({FIELD, TYPE})
public @interface ThreadConfinement {
    String value() default "UiThread";
}
