package reflect.serializeImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 17/3/31.
 */
public class SerializedClass {

    private List<SerializedField> fields = new ArrayList<>();


    public void add(SerializedField serializedField) {
        fields.add(serializedField);
    }
}
