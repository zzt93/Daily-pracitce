package pipeFilter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zzt on 1/14/16.
 * <p>
 * Usage:
 */
public class QueuePipe {

    BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);

    public void put(String s) throws InterruptedException {
        queue.put(s);
    }

    public String take() throws InterruptedException {
        return queue.take();
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of this queue, or null if this queue is empty
     */
    public String peek() {
        return queue.peek();
    }
}
