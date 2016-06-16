package com.example.zzt.whyfi.common;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.CLASS;
/**
 * Created by zzt on 6/11/16.
 * <p/>
 * Usage:
 */
@Documented
@Retention(CLASS)
@Target({METHOD,CONSTRUCTOR})
public @interface ToGuard {
    String value() default "";
    String[] values() default {};
}
