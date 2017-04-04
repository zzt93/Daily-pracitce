package reflect.serializeImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 17/3/31.
 */
public class SerializedClass {

    private Choice<Class, List<SerializedField>> choice = new Choice<>();

    SerializedClass(Class<?> aClass) {
        choice.setFirst(aClass);
    }

    SerializedClass() {
        choice.setSecond(new ArrayList<>());
    }


    public void add(SerializedField serializedField) {
        choice.getSecond().add(serializedField);
    }

    public SerializedField getField(Field field) {
        return null;
    }
}
