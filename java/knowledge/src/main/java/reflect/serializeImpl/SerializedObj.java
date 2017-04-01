package reflect.serializeImpl;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzt on 17/3/31.
 */
public class SerializedObj {

    private SerializedClass serializedClass;
    private Map<SerializedField, SerializedObj> fieldValue = new HashMap<>();


    public SerializedObj(SerializedClass serializedClass) {
        this.serializedClass = serializedClass;
    }

    public SerializedObj(Class<? extends Field> aClass, Object o) {
    }

    public SerializedObj put(SerializedField key, SerializedObj value) {
        return fieldValue.put(key, value);
    }
}
