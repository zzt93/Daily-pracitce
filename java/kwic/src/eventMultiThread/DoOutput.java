package eventMultiThread;

import event.SortedEvent;
import event.basic.EventRouter;
import event.basic.Input;
import eventMultiThread.basic.MultiThreadInputHandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zzt on 1/31/16.
 * <p>
 * Usage:
 */
public class DoOutput implements MultiThreadInputHandler {

    private BlockingQueue<Input> queue = new ArrayBlockingQueue<Input>(100);

    public DoOutput() {
        EventRouter.register(SortedEvent.class, this);
    }

    @Override
    public void run() {
        while (true) {
            Input input = null;
            try {
                input = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                continue;
            }
            input.getInputs().forEach(System.out::println);
        }
    }

    @Override
    public BlockingQueue<Input> getQueue() {
        return queue;
    }
}
