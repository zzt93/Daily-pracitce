package generic.learn;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by zzt on 3/10/17.
 * <p>
 * <h3></h3>
 */
public class ToArray {

    public static void main(String[] args) {
        //        testClassToString();
        testGetGenericArrayFromList(1, 2, 3);
    }

    private static <T> void testGetGenericArrayFromList(T... ints) {
        ArrayList<T> ts = new ArrayList<>();
        Collections.addAll(ts, ints);
        toArray(ts);
    }

    private static void toArray(List ts) {
        Class<?> aClass = ts.get(0).getClass();
        ts.toArray((Object[]) Array.newInstance(aClass, 0));
    }

    private static void testClassToString() {
        ArrayList<Class<?>> list = new ArrayList<>();
        list.add(Integer.class);
        list.add(String.class);
        final Class[] a = list.toArray(new Class[0]);
        System.out.println(Arrays.toString(a));
    }
}
