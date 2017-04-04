package reflect.serializeImpl;

import org.junit.Test;

/**
 * Created by zzt on 17/4/1.
 */
public class SerializerTest {

    private Serializer serializer;

    @org.junit.Before
    public void setUp() throws Exception {
        serializer = new Serializer();
    }

    @org.junit.Test
    public void serialize() throws Exception {
        serializer.serialize(new SerialClassA());
    }

    @Test
    public void test() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        int[] ints = new int[4];
        Class<?> aClass1 = Class.forName(ints.getClass().getName());
        Object o = aClass1.newInstance();
    }

}