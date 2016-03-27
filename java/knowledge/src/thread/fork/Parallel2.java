package thread.fork;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by zzt on 3/25/16.
 * <p>
 * Usage:
 */
public class Parallel2 extends RecursiveTask<int[]> {

    private int[] target;

    public Parallel2(int[] target) {
        this.target = target;
    }

    @Override
    protected int[] compute() {
        int[] res;
        int length = target.length;
        if (length < ParallelSort.THRESHOLD) {
            Arrays.sort(target);
            res = target;
        } else {
            int split = length / 2;

            Parallel2 left = new Parallel2(Arrays.copyOfRange(target, 0, split));
            Parallel2 right = new Parallel2(Arrays.copyOfRange(target, split, length));
            left.fork();
            int[] compute = right.compute();
            res = ParallelSort.merge(left.join(), compute);
        }
        return res;
    }

    public static void main(String[] args) {
        Random random = new Random();
        int[] target = random.ints(30, 0, 100).toArray();
        Parallel2 parallel2 = new Parallel2(target);
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        // Only sequential code should call invoke to begin parallelism
        int[] res = forkJoinPool.invoke(parallel2);
        System.out.println(Arrays.toString(res));
    }
}
