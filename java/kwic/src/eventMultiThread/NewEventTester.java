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
        ExecutorService executor = Executors.newCachedThreadPool();

        DoInput doInput = new DoInput(new MyIn("test"));
        DoShift doShift = new DoShift();
        DoSort doSort = new DoSort();
        DoOutput doOutput = new DoOutput();

        executor.execute(doInput);
        executor.execute(doShift);
        executor.execute(doSort);
        executor.execute(doOutput);
        // without it, this program will not finish
        executor.shutdown();
    }
}
