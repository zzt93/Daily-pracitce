package reflect.serializeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by zzt on 17/3/31.
 */
public class Serializer {

    private HashMap<Object, SerializedObj> map = new HashMap<>();

    public SerializedObj serialize(Object o) throws IllegalAccessException {
        HashSet<Object> serializing = new HashSet<>();
        return getSerializedObj(o, serializing);
    }

    private SerializedObj getSerializedObj(Object o, HashSet<Object> serializing) throws IllegalAccessException {
        if (map.containsKey(o)) {
            return map.get(o);
        }
        SerializedClass serializedClass = new SerializedClass();
        SerializedObj serializedObj = new SerializedObj(serializedClass);
        if (serializing.contains(o)) {
            return serializedObj;
        }

        serializing.add(o);

        for (Field field : o.getClass().getDeclaredFields()) {
            if (Modifier.isTransient(field.getModifiers())) {
                continue;
            }
            SerializedField type = new SerializedField(field);
            serializedClass.add(type);
            field.setAccessible(true);
            if (field.getClass().isPrimitive()) {
                serializedObj.put(type, new SerializedObj(field.getClass(), field.get(o)));
            } else {
                serializedObj.put(type, getSerializedObj(field.get(o), serializing));
            }
        }
        map.put(o, serializedObj);
        serializing.remove(o);
        return serializedObj;
    }
}
