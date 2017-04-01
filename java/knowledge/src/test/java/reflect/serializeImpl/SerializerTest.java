package reflect.serializeImpl;

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

}