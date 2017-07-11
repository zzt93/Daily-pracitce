package lambda;

/**
 * @author zzt
 */
public class WhyFinal {

    private static String test = "test";

    /**
     * Because inner class may run at any time, it have to
     * copy the reference of **local variable** for it to use later.
     * If you let the code to assign that local variable, programmer
     * may mistakenly think inner class can reflect the new change, which is
     * wrong, so Java simply forbid this behavior, i.e. make it `final` if we
     * want to change the variable reference.
     *
     * Furthermore, we don't need to make fields of object and class static variables
     * final, because inner class/lambda have the reference to that object/class, so need
     * to remember a single ref.
     */
    public static void main(String[] args) {
        primitive();
        primitiveLambda();
        string();
        stringLambda();
    }

    private static void stringLambda() {
        String s = "1";
//        s = "2";
        new Thread(() -> System.out.println(s));
//        s = "3";
    }

    private static void primitiveLambda() {
        int a = 1;
//        a = 2;
        new Thread(() -> System.out.println(a));
//        a = 3;
    }

    private static void string() {
        String s = "1";
        // change field works fine, no need to
        test = "a";
//        s = "2";
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(s);
                System.out.println(test);
            }
        });
//        s = "3";
    }

    private static void primitive() {
        int a = 1;
//        a = 2;
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(a);
            }
        });
//        a = 3;
    }
}
