package reflect.serializeImpl;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * Created by zzt on 17/3/31.
 */
public class SerializedField {

    private SerializedClass serializedClass;
    private String name;

    public SerializedField(Field field) {
        Class<?> declaringClass = field.getDeclaringClass();

        if (declaringClass.isPrimitive()) {
        } else if (declaringClass.isArray() || Collection.class.isAssignableFrom(declaringClass)) {
        } else {

        }
    }
}
