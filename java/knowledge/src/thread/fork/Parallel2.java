package thread.fork;

import java.util.Arrays;
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

            Parallel2 t1 = new Parallel2(Arrays.copyOfRange(target, 0, split));
            Parallel2 t2 = new Parallel2(Arrays.copyOfRange(target, split, length));
            t1.fork();
            int[] compute = t2.compute();
            res = ParallelSort.merge(t1.join(), compute);

        }
        return res;
    }
}
