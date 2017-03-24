package generic;

import java.util.Arrays;

/**
 * Created by zzt on 4/14/16.
 * <p>
 * Usage:
 */
public class LearnArray {

    public <T> T[] a() {
        Object[] objects = new Object[3];
        return (T[]) objects;
    }

    public void b() {
        int[] ints = new int[3];
        /**
         * compiler error
         */
        //        String[] strings = (String[]) ints;
    }

    public static void main(String[] args) {
        /**
         * java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to [Ljava.lang.Integer;
         */
        Integer[] a = new LearnArray().<Integer>a();
        a[0] = 1;
        System.out.println(Arrays.toString(a));
    }
}
