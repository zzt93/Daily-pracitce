package reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zzt on 12/30/15.
 * <p>
 * Usage:
 */
interface Inter {
    void some();
}

class Real implements Inter {

    @Override
    public void some() {
        System.out.println("do some thing");
    }
}


public class DynamicProxy implements InvocationHandler {
    private Object proxied;

    public DynamicProxy(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invocation");
        Object invoke = method.invoke(proxied, args);
        System.out.println("After invocation");
        return invoke;
    }

    public static void main(String[] args) throws Throwable {
        Inter inter = (Inter) Proxy.newProxyInstance(
                Inter.class.getClassLoader(),
                new Class[]{Inter.class},
                new DynamicProxy(new Real())
        );
        inter.some();
    }
}
