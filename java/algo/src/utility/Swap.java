package utility;

import java.util.ArrayList;

/**
 * Created by zzt on 3/23/15.
 */
public class Swap {
    public static <T> void swap(ArrayList<T> ts, int i, int j) {
        T tmp = ts.get(i);
        ts.set(i, ts.get(j));
        ts.set(j, tmp);
    }

    public static <T> void swap(T[] ts, int i, int j) {
        T tmp = ts[i];
        ts[i] = ts[j];
        ts[j] = tmp;
    }
}
