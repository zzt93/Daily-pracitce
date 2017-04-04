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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SerializedField that = (SerializedField) o;

        return fieldClass.equals(that.fieldClass) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = fieldClass.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return fieldClass.getName() + " " + name + ";\n";
    }
}
