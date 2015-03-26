package utility;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zzt on 3/21/15.
 */
public class Selection {

    public static <T extends  Comparable<T>> int[] dualPivotPartition(ArrayList<T> ts, int from, int to) {
        T p1 = ts.get(from);
        T p2 = ts.get(to-1);

        return new int[0];//FIXME finish it
    }

    /**
     * *   left part    center part              right part
     * +-------------------------------------------------+
     * |  < pivot  |   == pivot   |     ?    |  > pivot  |
     * +-------------------------------------------------+
     *              ^              ^        ^
     *              |              |        |
     *             less            k      great
     *
     * @param ts
     * @param from
     * @param to
     * @param <T>
     * @return less and k
     */
    public static <T extends  Comparable<T>> int[] threeWayPartition(ArrayList<T> ts, int from, int to) {
        int[] res = new int[2];

        T p = ts.get(from);
        from++;
        while (from < to) {
            T tmp = ts.get(from);
            int i = tmp.compareTo(p);
            if(i < 0) {

            } else if (i > 0) {

            } else {

            }
            from++;
        }
        return res;
    }

    /**
     * *   left part    center part              right part
     * +-------------------------------------------------+
     * |p|  <= pivot  |          ?           |  > pivot  |
     * +-------------------------------------------------+
     *                 ^                    ^
     *                 |                    |
     *                le                  great
     *
     * @param ts
     * @param from
     * @param to
     * @param <T>
     * @return
     */
    private static <T extends Comparable<T>> int partition(ArrayList<T> ts, int from, int to) {
        T p = ts.get(from);
        int le = from + 1;
        int great = --to;
        while (le <= great) {//even le == great, we should go into it
            //find large
            while (ts.get(le).compareTo(p) <= 0) {
                le++;
            }
            //find small
            while (ts.get(great).compareTo(p) > 0) {
                great--;
            }
            if (le > great) {//TODO whether to have equal
                break;
            }
            Swap.swap(ts, le, great);

        }
        Swap.swap(ts, from, great);
        return great;
    }


    /**
     * return the element at k-th at range of [from, to), counting from 0
     * @param ts
     * @param k
     * @param from inclusive
     * @param to exclusive
     * @param <T>
     * @return
     */
    private static <T extends  Comparable<T>> T selection(ArrayList<T> ts, int k, int from, int to) {
        if (from + 1 == to) {
            return ts.get(from);
        }
        int pIndex = partition(ts, from, to);
        if (pIndex < k) {
            return selection(ts, k, pIndex + 1, to);
        } else if (pIndex > k) {
            return selection(ts, k, from, pIndex);
        } else {
            return ts.get(k);
        }

    }



    /**
     * return the element at k-th element (counting from 0)
     * @param ts -- collection
     * @param k -- k-th
     * @param <T> -- type
     * @return type
     */
    public static <T extends  Comparable<T>> T selection(ArrayList<T> ts, int k) {
        inRangeCheck(ts.size(), k);
        //TODO add shuffle
        return selection(ts, k, 0, ts.size());
    }

    private static void inRangeCheck(int size, int k) {
        if (k >= size) {
            throw new IllegalArgumentException("k is too large");
        }
    }

    public static void main(String[] args) {
        MyIn in;
        try {
            in = new MyIn(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        while (in.hasNext()) {
            tmp.add(in.nextInt());
        }
        Integer[] a = tmp.toArray(new Integer[tmp.size()]);
        Arrays.sort(a);

        MyOut out = new MyOut(args[1]);
        for (int i = 1; i < tmp.size(); i*=2) {
            out.print(a[i]);
            out.print("======");
            out.println(selection(tmp, i));

        }
        out.close();
//        out.println(selection(tmp, 64));
    }
}
