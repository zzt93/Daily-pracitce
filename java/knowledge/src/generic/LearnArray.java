package generic;

import java.util.Objects;

/**
 * Created by zzt on 4/14/16.
 * <p>
 * Usage:
 */
public class LearnArray {

    public <T> T[] a() {
        Object[] objects = new Object[3];
        return (T[])objects;
    }

    public void b() {
        int[] ints = new int[3];
//        String[] strings = (String[]) ints;
    }

    public static void main(String[] args) {
        Integer[] a = new LearnArray().<Integer>a();
    }
}
