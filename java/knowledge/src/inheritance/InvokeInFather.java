package inheritance;

/**
 * Created by zzt on 11/28/15.
 * <p>
 * Usage:
 */


class Father {
    public void f() {
        System.out.println("in father.f()");
        g();
    }

    public void g() {
        System.out.println("in father.g()");
    }
}

class Son extends Father {
//    @Override
//    public void f() {
//        System.out.println("in son.f()");
//        g();
//    }

    @Override
    public void g() {
        System.out.println("in son.g()");
    }
}


public class InvokeInFather {
    public static void main(String[] args) {
        new Son().f();
    }
}
