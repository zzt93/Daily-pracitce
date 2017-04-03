package net.rmiImpl;

/**
 * Created by zzt on 4/3/17.
 * <p>
 * <h3></h3>
 */
public class InterfaceImpl implements AInterface {
    @Override
    public void f() {
        System.out.println(InterfaceImpl.class + ": f()");
    }

    @Override
    public String str(int a) {
        System.out.println(InterfaceImpl.class + ": str(int a)");
        return Integer.toString(a);
    }
}
