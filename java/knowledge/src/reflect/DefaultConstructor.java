package reflect;

/**
 * Created by zzt on 10/4/16.
 * <p>
 * <h3></h3>
 */
public class DefaultConstructor {

    private static class B {
        private int a;
        private String b;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        B.class.newInstance();
    }
}
