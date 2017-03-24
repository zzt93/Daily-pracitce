package annotation;

import java.lang.annotation.*;

/**
 * Created by zzt on 4/17/16.
 * <p>
 * Usage:
 */
@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.SOURCE)
public @interface GuardedBy {
    Class type();

    String varName();
}
