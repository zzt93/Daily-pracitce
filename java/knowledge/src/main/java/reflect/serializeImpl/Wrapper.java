package reflect.serializeImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zzt on 4/16/17.
 * <p>
 * <h3></h3>
 */
public class Wrapper {

    private static Set<Class<?>> ret = new HashSet<Class<?>>();

    static {
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
    }

    public static boolean contains(Class<?> o) {
        return ret.contains(o);
    }
}
