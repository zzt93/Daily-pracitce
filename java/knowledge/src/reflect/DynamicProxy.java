package reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by zzt on 12/30/15.
 * <p>
 * Usage:
 */
public class DynamicProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invocation");
        Object invoke = method.invoke(proxy, args);
        System.out.println("After invocation");
        return invoke;
    }

    public static void main(String[] args) throws Throwable {
        String num = "12";
        Object res = new DynamicProxy().invoke(num,
                num.getClass().getMethod("charAt", Integer.class), new Object[]{0});
    }
}
