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
}
