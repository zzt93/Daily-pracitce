package pipeFilter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 1/14/16.
 * <p>
 * Usage:
 */
public class FixedSizeQueuePipe implements QueuePipe {

    BlockingQueue<StringMessage> queue;

    public FixedSizeQueuePipe(int size) {
        queue = new ArrayBlockingQueue<>(size);
    }

    @Override
    public void put(StringMessage s) throws InterruptedException {
        queue.put(s);
    }

    @Override
    public StringMessage take() throws InterruptedException {
        return queue.take();
    }

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of this queue, or null if this queue is empty
     */
    @Override
    public StringMessage peek() {
        return queue.peek();
    }

    @Override
    public StringMessage poll(long timeout) throws InterruptedException {
        return queue.poll(timeout, TimeUnit.SECONDS);
    }
}
