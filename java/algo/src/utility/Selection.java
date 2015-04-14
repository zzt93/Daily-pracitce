package utility;

/**
 * Created by zzt on 3/22/15.
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by zzt on 3/21/15.
 */
public class Selection {

    /**
     * *   left part    center part              right part
     * +-------------------------------------------------+
     * |p|  <= pivot  |          ?           |  > pivot  |
     * +-------------------------------------------------+
     * ^                    ^
     * |                    |
     * le                  great
     *
     * @param ts
     * @param from
     * @param to
     * @param <T>
     * @return
     */
    private static <T> int partition(ArrayList<T> ts, int from, int to, Comparator<? super T> comparator) {
        T p = ts.get(from);
        int le = from + 1;
        int great = --to;
        while (le <= great) {//even le == great, we should go into it
            //find large
            while (comparator.compare(ts.get(le), (p)) <= 0) {
                le++;
            }
            //find small
            while (comparator.compare(ts.get(great), (p)) > 0) {
                great--;
            }
            if (le > great) {//TODO whether to have equal
                break;
            }
            swap(ts, le, great);

        }
        swap(ts, from, great);
        return great;
    }

    private static <T> void swap(ArrayList<T> ts, int i, int j) {
        T tmp = ts.get(i);
        ts.set(i, ts.get(j));
        ts.set(j, tmp);
    }


    /**
     * return the element at k-th at range of [from, to), counting from 0
     *
     * @param ts
     * @param k
     * @param from inclusive
     * @param to   exclusive
     * @param <T>
     * @return
     */
    private static <T> T selection(ArrayList<T> ts, int k, int from, int to, Comparator<? super T> comparator) {
        if (from + 1 == to) {
            return ts.get(from);
        }
        int pIndex = partition(ts, from, to, comparator);
        if (pIndex < k) {
            return selection(ts, k, pIndex + 1, to, comparator);
        } else if (pIndex > k) {
            return selection(ts, k, from, pIndex, comparator);
        } else {
            return ts.get(k);
        }

    }


    /**
     * return the element at k-th small element (counting from 0)
     *
     * @param ts  -- collection
     * @param k   -- k-th
     * @param <T> -- type
     * @return type
     */
    public static <T extends Comparable<T>> T selection(ArrayList<T> ts, int k) {
        inRangeCheck(ts.size(), k);
        //TODO add shuffle
        //TODO may change it to double if speed is primary concern
        return selection(ts, k, 0, ts.size(), NaturalOrder.INSTANCE);
    }

    public static <T> T selection(ArrayList<T> ts, int k, Comparator<T> comparator) {
        inRangeCheck(ts.size(), k);
        return selection(ts, k, 0, ts.size(), comparator);
    }

    private static void inRangeCheck(int size, int k) {
        if (k >= size) {
            throw new IllegalArgumentException("k is too large");
        }
    }

    static final class NaturalOrder implements Comparator<Object> {
        @SuppressWarnings("unchecked")
        public int compare(Object first, Object second) {
            return ((Comparable<Object>)first).compareTo(second);
        }
        static final NaturalOrder INSTANCE = new NaturalOrder();
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
