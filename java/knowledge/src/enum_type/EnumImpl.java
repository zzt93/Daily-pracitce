package enum_type;

/**
 * Created by zzt on 12/23/16.
 * <p>
 * <h3></h3>
 */
public enum EnumImpl {

    A {
        public static final int a = 1;
    },
    B {
        //          public static int b;  //Illegal static declaration in inner class

        public void method() {
            //            int aa = A.a; // cannot find symbol
            //                System.out.println(s); // non-static variable s cannot be referenced from a static context
        }
    };

    //    abstract void method();
    private int s;
}

abstract class Cmp {

    static class FIRST extends Cmp {
        public static final int a = 1;
        //        public static int b;

        //        private enum T {
        //
        //        }
    }

    //    public static final Cmp A;

    public static void main(String[] args) {
        //        int a = A.a;
    }
}
