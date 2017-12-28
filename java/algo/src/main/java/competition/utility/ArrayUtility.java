package competition.utility;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Created by zzt on 3/30/15.
 */
public class ArrayUtility {

    private static Random random = new Random();

    public static int[] arrayListToArrayInt(ArrayList<Integer> integers) {
        int[] res = new int[integers.size()];
        for (int i = 0; i < integers.size(); i++) {
            res[i] = integers.get(i);
        }
        return res;
    }

    public static double[] arrayListToArrayDouble(ArrayList<Double> doubles) {
        double[] res = new double[doubles.size()];
        for (int i = 0; i < doubles.size(); i++) {
            res[i] = doubles.get(i);
        }
        return res;
    }

    /**
     * @param ts
     * @param from -- inclusive
     * @param end  -- exclusive
     * @param key  -- the aim to find
     * @param <T>  -- type
     *
     * @return -- the index of key or -1
     */
    public static <T extends Comparable<T>> int binarySearch(ArrayList<T> ts, int from, int end,
                                                             T key) {
        if (from >= end) {
            throw new IllegalArgumentException("From index:" + from + " is larger than ending " +
                    "index:" + end);
        }
        while (from < end) {
            int mid = (from + end) / 2;
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

    public static <T extends Comparable<? super T>> boolean isSorted(List<T> ts, boolean
            ascending) {
        final ListIterator<T> it = ts.listIterator();
        it.next();
        while (it.hasNext()) {
            final T pre = it.previous();
            it.next();
            final T next = it.next();
            int res = pre.compareTo(next);
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

    public static Integer[] randomIntegers(long seed, long streamSize, int randomNumberOrigin,
                                           int randomNumberBound) {
        random.setSeed(seed);
        return random
                .ints(streamSize, randomNumberOrigin, randomNumberBound)
                .boxed()
                .collect(Collectors.toList()).toArray(new Integer[0]);
    }

    public static int[] randomInts(long seed, long streamSize, int randomNumberOrigin,
                                   int randomNumberBound) {
        random.setSeed(seed);
        return random
                .ints(streamSize, randomNumberOrigin, randomNumberBound)
                .toArray();
    }

    public static ArrayList<Integer> randomIntList(long seed, long streamSize, int
            randomNumberOrigin,
                                                   int randomNumberBound) {
        random.setSeed(seed);
        return random
                .ints(streamSize, randomNumberOrigin, randomNumberBound)
                .boxed()
                .collect(Collectors.toCollection(ArrayList<Integer>::new));
    }


    public static double[] randomDoubles(long seed, long streamSize, int randomNumberOrigin,
                                         int randomNumberBound) {
        random.setSeed(seed);
        return random
                .doubles(streamSize, randomNumberOrigin, randomNumberBound)
                .toArray();
    }

    public static void main(String[] args) {
        MyIn in;
        try {

            in = new MyIn("testCase/binarySearch.txt");

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
