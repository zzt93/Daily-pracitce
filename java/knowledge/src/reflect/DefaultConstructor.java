package reflect;

/**
 * Created by zzt on 10/4/16.
 * <p>
 * <h3></h3>
 */
public class DefaultConstructor {

    private static class A {
        private int b;

//        public A(Object o) {
//        }
    }

    private static class B extends A {
        private String b;
        private int a;
        private float f;

//        public B() {
//            super(null);
//        }
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        B.class.newInstance();
    }
}
