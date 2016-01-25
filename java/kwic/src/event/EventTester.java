package event;

import event.basic.EventRouter;
import utility.MyIn;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 1/25/16.
 * <p>
 * Usage:
 */
public class EventTester {

    public static void main(String[] args) throws FileNotFoundException {
        ExecutorService executor = Executors.newCachedThreadPool();

        DoInput doInput = new DoInput(new MyIn("test"));
        DoShift doShift = new DoShift();
        DoSort doSort = new DoSort();
        DoSort.PrepareSort prepareSort = doSort.new PrepareSort();
        DoOutput doOutput = new DoOutput();
        EventRouter.register(SimpleInputEvent.class, doShift);
        EventRouter.register(InputFinishedEvent.class, doSort);
        EventRouter.register(ShiftedInputEvent.class, prepareSort);
        EventRouter.register(SortedEvent.class, doOutput);

        executor.execute(doInput);
    }
}
