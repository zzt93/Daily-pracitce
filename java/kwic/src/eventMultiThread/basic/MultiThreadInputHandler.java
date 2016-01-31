package eventMultiThread.basic;

import event.basic.Input;
import event.basic.InputHandler;

import java.util.concurrent.BlockingQueue;

/**
 * Created by zzt on 1/31/16.
 * <p>
 * Usage:
 */
public interface MultiThreadInputHandler extends Runnable, InputHandler {

    default void receive(Input input) {
        BlockingQueue<Input> queue = getQueue();
        try {
            queue.put(input);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    BlockingQueue<Input> getQueue();
}
