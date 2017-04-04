package reflect.serializeImpl;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * Created by zzt on 17/3/31.
 */
public class SerializedField {

    private SerializedClass fieldClass;
    private String name;

    public SerializedField(SerializedClass fieldClass, String name) {
        this.fieldClass = fieldClass;
        this.name = name;
    }
}
