package reflect.serializeImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zzt on 17/3/31.
 */
public class SerializedObj {

    private SerializedClass serializedClass;
    /**
     * Primitive or Array or Other class
     */
    private TriChoice<Object, Map<Integer, SerializedObj>, Map<SerializedField, SerializedObj>> choice = new TriChoice<>();


    SerializedObj(SerializedClass aClass, Object value) {
        this.serializedClass = aClass;
        choice.setFirst(value);
    }

    SerializedObj(SerializedClass elementClass) {
        this.serializedClass = elementClass;
        choice.setSecond(new HashMap<>());
    }

    public SerializedObj(boolean third, SerializedClass serializedClass) {
        this.serializedClass = serializedClass;
        choice.setThird(new HashMap<>());
    }

    public SerializedObj put(SerializedField key, SerializedObj value) {
        return choice.getThird().put(key, value);
    }

    public SerializedObj put(Integer key, SerializedObj value) {
        return choice.getSecond().put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("class: ").append(serializedClass.getName()).append("\n");
        if (choice.isFirst()) {
            sb.append(choice.getFirst());
        } else if (choice.isThird()) {
            choice.getThird().forEach((k, v) -> {
                // recursion here: v.toString()
                sb.append(k).append("\n").append(v.toString());
            });
        } else {
            sb.append("[");
            Map<Integer, SerializedObj> arrayContent = choice.getSecond();
            for (Integer integer : arrayContent.keySet()) {
                sb.append(arrayContent.get(integer)).append(", ");
            }
            sb.append("]");
        }
        return sb.toString();

    }
}
