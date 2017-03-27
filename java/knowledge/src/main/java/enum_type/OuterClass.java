package main.java.enum_type;

/**
 * Created by zzt on 10/9/16.
 * <p>
 * <h3></h3>
 */
public class OuterClass {

    private static int b = 1;
    private int a = 1;

    public OuterClass() {
        // access the NestedClass's private field
        System.out.println(NestedClass.na);
        //access the NestedClass's private method
        NestedClass.f(b);
    }

    private class InnerClass {
        InnerClass() {
            System.out.println(a);
        }
    }

    private static class NestedClass {
        private static int na;

        {
            //access the OuterClass's private field
            System.out.println(b);
        }

        private static void f(int a) {
        }
    }

    public static void main(String[] args) {
        //access the NestedClass's private method
        new NestedClass();
    }
}
