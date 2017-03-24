package thread.fork;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Created by zzt on 3/25/16.
 * <p>
 * Usage:
 */
public class ParallelSort extends RecursiveAction {

    public static final int THRESHOLD = 10;
    private int[] target;
    private int start;
    private int end;

    public ParallelSort(int[] target, int start, int end) {
        this.target = target;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            sortIt();
        } else {
            int split = start + length / 2;

            ParallelSort t1 = new ParallelSort(target, start, split);
            ParallelSort t2 = new ParallelSort(target, split, end);
            invokeAll(t1, t2);
            int[] merge = merge(Arrays.copyOfRange(target, start, split), Arrays.copyOfRange(target, split, end));
            System.arraycopy(merge, 0, target, start, end - start);
        }
    }

    public static int[] merge(int[] ints, int[] second) {
        int[] res = new int[ints.length + second.length];
        int fi = 0;
        int si = 0;
        int i;
        for (i = 0; fi < ints.length && si < second.length; i++) {
            if (ints[fi] <= second[si]) {
                res[i] = ints[fi++];
            } else {
                res[i] = second[si++];
            }
        }
        if (fi == ints.length) {
            for (; si < second.length; si++) {
                res[i++] = second[si];
            }
        } else {
            for (; fi < ints.length; fi++) {
                res[i++] = ints[fi];
            }
        }
        return res;
    }

    private void sortIt() {
        Arrays.sort(target, start, end);
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] target = random.ints(30, 0, 100).toArray();
        ParallelSort parallelSort = new ParallelSort(target, 0, target.length);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.invoke(parallelSort);
        System.out.println(Arrays.toString(target));
    }
}
