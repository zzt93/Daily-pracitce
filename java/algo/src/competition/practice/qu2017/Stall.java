package competition.practice.qu2017;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by zzt on 4/8/17.
 * <p>
 * <h3></h3>
 */
public class Stall {

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/stall-large-practice.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        long[] res;
        for (int i = 0; i < trail; i++) {
            res = stall3(in.nextLong(), in.nextLong());
            out.println("Case #" + (i + 1) + ": " + res[0] + " " + res[1]);
            //            out.printf("Case #%d: %.7f\n", (i + 1), res);
        }
    }

    private static long[] stall(long n, long k) {
        PriorityQueue<Long> heap = new PriorityQueue<>(Comparator.comparingLong(Long::longValue).reversed());
        heap.add(n);
        long gap = heap.poll();
        long l = 0;
        long r = 0;
        for (long i = 0; i < k; i++) {
            l = (gap - 1) / 2;
            r = gap - 1 - l;
            heap.add(l);
            heap.add(r);
            gap = heap.poll();
        }
        return new long[]{r, l};
    }

    private static long[] stall2(long n, long k) {
        PriorityQueue<Long> heap = new PriorityQueue<>(Comparator.comparingLong(Long::longValue).reversed());
        heap.add(n);
        long gap = heap.poll();
        long l = 0;
        long r = 0;
        long log = (long) (Math.log(k + 1) / Math.log(2));
        long mid = (long) Math.pow(2, log) - 1;
        for (long i = 0; i < mid; i++) {
            l = (gap - 1) / 2;
            r = gap - 1 - l;
            heap.add(l);
            heap.add(r);
            gap = heap.poll();
        }
        for (long i = mid; i < k; i++) {
            l = (gap - 1) / 2;
            r = gap - 1 - l;
            gap = heap.poll();
        }
        return new long[]{r, l};
    }


    private static long[] stall3(long n, long k) {
        long log = (long) (Math.log(k + 1) / Math.log(2));
        long tempK = (long) Math.pow(2, log) - 1;
        long parts = tempK + 1;
        long left = n - tempK;
        long min = left / parts;
        long max = min + 1;
        if (min * parts == left) {
            max = min;
        }
        long maxCount = left - parts * min;
        long minCount = parts - maxCount;
        if (k - tempK == 0) {
            if (maxCount >= minCount) {
                return new long[]{max, min};
            }
            return new long[]{min, min};
        }
        if (k - tempK <= maxCount) {
            max--;
            return new long[]{max - max / 2, max / 2};
        }
        min--;
        return new long[]{min - min / 2, min / 2};
    }
}
