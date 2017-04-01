package reflect.typeInfo;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by zzt on 7/19/16.
 * <p>
 * <h3></h3>
 */
public class FieldTest {

    public static int test(String[] args) {
        int pass = 0;
        for (String arg : args) {
            try {
                final Class<?> aClass = Class.forName(arg);
                aClass.getMethod("test").invoke(aClass.newInstance());
                pass++;
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                System.out.printf("%s failed: %s%n", arg, e);
            }
        }
        return pass;
    }
}
