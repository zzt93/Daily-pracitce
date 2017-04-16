package reflect.serializeImpl;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by zzt on 17/3/31.
 */
public class SerializedClass {

    private String name;
    /**
     * Primitives or Array or other class
     */
    private TriChoice<Class, SerializedClass, HashMap<Field, SerializedField>> choice = new TriChoice<>();

    SerializedClass(Class<?> aClass) {
        choice.setFirst(aClass);
        name = aClass.getName();
    }

    SerializedClass(SerializedClass serializedClass) {
        choice.setSecond(serializedClass);
        name = "Array";
    }

    SerializedClass(String name) {
        this.name = name;
        choice.setThird(new HashMap<>());
    }


    public void put(Field field, SerializedField serializedField) {
        choice.getThird().put(field, serializedField);
    }

    public SerializedField getField(Field field) {
        return choice.getThird().get(field);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SerializedClass that = (SerializedClass) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name).append(" {\n");
        if (choice.isFirst()) {
            // primitive or boxing type, not show content in it
        } else if (choice.isThird()) {
            for (SerializedField serializedField : choice.getThird().values()) {
                sb.append(serializedField.toString());
            }
        } else {
            sb.append(choice.getSecond());
        }
        sb.append("}\n\n");
        return sb.toString();
    }
}
