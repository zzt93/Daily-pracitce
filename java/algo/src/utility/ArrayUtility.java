package utility;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by zzt on 3/30/15.
 */
public class ArrayUtility {

    public static int[] arraylist_to_array_int(ArrayList<Integer> integers) {
        int[] res = new int[integers.size()];
        for (int i = 0; i < integers.size(); i++) {
            res[i] = integers.get(i);
        }
        return res;
    }

    public static double[] arraylist_to_array_double(ArrayList<Double> doubles) {
        double[] res = new double[doubles.size()];
        for (int i = 0; i < doubles.size(); i++) {
            res[i] = doubles.get(i);
        }
        return res;
    }

    /**
     *
     * @param ts
     * @param from -- inclusive
     * @param end -- exclusive
     * @param key -- the aim to find
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> int binarySearch(ArrayList<T> ts, int from, int end, T key) {
        if (from >= end) {
            throw new IllegalArgumentException("From index:" + from + " is larger than ending index:" + end);
        }
        while (from < end) {
            int mid = (from + end)/2;
            int res = key.compareTo(ts.get(mid));
            if (res == 0) {
                return mid;
            } else if (res > 0) {
                from = mid + 1;
            } else {
                end = mid;
            }
//            switch (res) {
//                case 0:
//                    return mid;
//                case 1:
//                    from = mid + 1;
//                    break;
//                case -1:
//                    end = mid;
//                    break;
//                default:
//                    assert false;
//            }
        }
        return -1;
    }

    public static <T extends Comparable<T>> boolean isSorted(ArrayList<T> ts, boolean ascending) {
        for (int i = 1; i < ts.size(); i++) {
            int res = ts.get(i-1).compareTo(ts.get(i));
            if (ascending) {
                if (res > 0) {
                   return false;
                }
            } else {
                if (res < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MyIn in;
        try {
            in = new MyIn("binarySearch.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        ArrayList data = new ArrayList();
        while (in.hasNext()) {
            data.add(in.nextInt());
        }
        boolean sorted = isSorted(data, true);
        if (!sorted) throw new AssertionError();
        for (int i = 0; i < data.size(); i++) {
            System.out.println(binarySearch(data, 0, data.size(), i));
        }

    }
}
