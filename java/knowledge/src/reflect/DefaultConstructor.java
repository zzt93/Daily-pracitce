package reflect;

/**
 * Created by zzt on 10/4/16.
 * <p>
 * <h3></h3>
 */
public class DefaultConstructor {

    private static class A {
        private int a;
    }

    //    private static class B extends A {
    //    }

//    static class C {
//    }
//
//    protected static class D {
//    }
//
//    private class Inner {
//    }
//
//    class InnerB {
//    }
//
//    protected class InnerC {
//    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        //        B.class.newInstance();
        int b = new A().a;
    }
}
