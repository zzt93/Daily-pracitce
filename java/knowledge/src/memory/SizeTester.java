package memory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 9/8/16.
 * <p>
 * <h3></h3>
 */
public class SizeTester {
    private static class JustString {
        private String a = "test";
    }

    private static class ByteArray {
        private byte[] a = new byte[128];
    }

    private static class JustObject {
        private Object object = new Object();
    }

    private static class JustList {
        private List<Object> objects = new ArrayList<>();
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        printSize(JustString.class);
        printSize(ByteArray.class);
        printSize(JustObject.class);
        printSize(JustList.class);
    }

    private static void printSize(Class<?> clazz) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        final Constructor<?> constructor1 = clazz.getDeclaredConstructors()[0];
        constructor1.setAccessible(true);
        System.out.println(clazz.getName() + ": "
                + ObjectSizeFetcher.getObjectSize(constructor1.newInstance((Object[]) null)));
    }
}
