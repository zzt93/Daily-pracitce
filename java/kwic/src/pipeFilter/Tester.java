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

    public static void main(String[] args) throws FileNotFoundException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        QueuePipe input2shift = new QueuePipe();
        QueuePipe shift2Sort = new QueuePipe();
        executor.execute(new DoInput(input2shift));
        executor.execute(new Shift(input2shift, shift2Sort));
    }
}
