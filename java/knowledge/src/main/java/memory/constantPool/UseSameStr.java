package memory.constantPool;

/**
 * Created by zzt on 6/29/16.
 * <p>
 * <h3></h3>
 */
public class UseSameStr {

    static {
        System.out.println("load class " + UseSameStr.class);
    }

    static void reportLoaded() {
        System.out.println("Loaded");
    }

    public static final String str2 = "string";

}
