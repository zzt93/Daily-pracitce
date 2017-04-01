package inter;

/**
 * Created by zzt on 17/3/31.
 */
public class MultiInheritance implements A, B {


    @Override
    public void f() {
        A.super.f();
    }
}

interface A {
    default void f() {
        System.out.println(A.class);
    }
}

interface B {
    default void f() {
        System.out.println(B.class);
    }
}
