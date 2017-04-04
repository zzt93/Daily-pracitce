package reflect.serializeImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzt on 17/3/31.
 */
public class SerializedObj {

    private SerializedClass serializedClass;
    private TriChoice<Object, Map<SerializedField, SerializedObj>, Map<Integer, SerializedObj>> choice = new TriChoice<>();


    SerializedObj(SerializedClass aClass, Object value) {
        this.serializedClass = aClass;
        choice.setFirst(value);
    }

    public SerializedObj(SerializedClass serializedClass) {
        this.serializedClass = serializedClass;
        choice.setSecond(new HashMap<>());
    }

    public SerializedObj put(SerializedField key, SerializedObj value) {
        return choice.getSecond().put(key, value);
    }

    public SerializedObj put(Integer key, SerializedObj value) {
        return choice.getThird().put(key, value);
    }
}
