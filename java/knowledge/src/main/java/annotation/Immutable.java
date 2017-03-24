package annotation;

import java.lang.annotation.*;

/**
 * Created by zzt on 4/17/16.
 * <p>
 * Usage:
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Immutable {
}
