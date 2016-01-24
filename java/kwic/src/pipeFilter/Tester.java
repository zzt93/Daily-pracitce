package pipeFilter;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 1/18/16.
 * <p>
 * Usage:
 */
public class Tester {

    public static final int SIZE = 100;

    public static void main(String[] args) throws FileNotFoundException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        QueuePipe input2shift = new FixedSizeQueuePipe(SIZE);
        QueuePipe shift2Sort = new UnlimitedQueuePipe();
        QueuePipe sort2output = new FixedSizeQueuePipe(SIZE);

        executor.execute(new DoInput(input2shift));
        executor.execute(new Shift(input2shift, shift2Sort));
        executor.execute(new Sort(shift2Sort, sort2output));
        executor.execute(new DoOutput(sort2output));
    }
}
