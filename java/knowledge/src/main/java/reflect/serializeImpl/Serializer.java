package reflect.serializeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by zzt on 17/3/31.
 */
public class Serializer {

    private HashMap<Object, SerializedObj> map = new HashMap<>();

    public SerializedObj serialize(Object o) throws IllegalAccessException {
        return getSerializedObj(o);
    }

    private SerializedObj getSerializedObj(Object o) throws IllegalAccessException {
        if (map.containsKey(o)) {
            return map.get(o);
        }
        Class<?> aClass = o.getClass();
        SerializedClass serializedClass = getSerializedClass(aClass);
        SerializedObj serializedObj;
        if (aClass.isPrimitive()) {
            serializedObj = new SerializedObj(serializedClass, o);
            map.put(o, serializedObj);
            return serializedObj;
        } else if (Iterable.class.isAssignableFrom(aClass)) {
            serializedObj = new SerializedObj(serializedClass);
            map.put(o, serializedObj);
            int i = 0;
            for (Object inner : ((Iterable) o)) {
                serializedObj.put(i++, getSerializedObj(inner));
            }
            return serializedObj;
        }
        serializedObj = new SerializedObj(serializedClass);
        map.put(o, serializedObj);


        for (Field field : aClass.getDeclaredFields()) {
            if (Modifier.isTransient(field.getModifiers())) {
                continue;
            }
            SerializedField type = serializedClass.getField(field);

            field.setAccessible(true);
            Object value = field.get(o);
            serializedObj.put(type, getSerializedObj(value));
        }
        map.put(o, serializedObj);
        return serializedObj;
    }

    private HashMap<Class, SerializedClass> classes = new HashMap<>();

    private SerializedClass getSerializedClass(Class aClass) {
        if (classes.containsKey(aClass)) {
            return classes.get(aClass);
        }
        SerializedClass serializedClass;
        if (isSystemClass(aClass)) {
            serializedClass = new SerializedClass(aClass);
            classes.put(aClass, serializedClass);
            return serializedClass;
        }
        serializedClass = new SerializedClass();
        classes.put(aClass, serializedClass);

        for (Field field : aClass.getDeclaredFields()) {
            SerializedField type = new SerializedField(
                    getSerializedClass(field.getType()), field.getName());
            serializedClass.add(type);
        }
        return serializedClass;
    }

    /**
     * simple way to test whether the class is a jdk class
     * test whether this class is loaded by bootstrap classloader
     *
     * @return whether the class is a jdk class
     */
    private boolean isSystemClass(Class aClass) {
        return aClass.getClassLoader() == null;
    }
}
