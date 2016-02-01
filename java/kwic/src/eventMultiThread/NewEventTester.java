package eventMultiThread;

import event.DoInput;
import utility.MyIn;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 1/31/16.
 * <p>
 * Usage:
 */
public class NewEventTester {
    public static void main(String[] args) throws FileNotFoundException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        DoInput doInput = new DoInput(new MyIn("test"));
        DoShift doShift = new DoShift();
        DoSort doSort = new DoSort();
        DoOutput doOutput = new DoOutput();

        executor.execute(doShift);
        executor.execute(doSort);
        executor.execute(doOutput);
        // make it start later, test implementation
        executor.execute(doInput);

        // This method does not wait for previously submitted tasks to complete execution
//        executor.shutdown();
    }
}
