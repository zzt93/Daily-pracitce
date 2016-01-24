package pipeFilter;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 1/24/16.
 * <p>
 * Usage:
 */
public class UnlimitedQueuePipe implements QueuePipe{

    BlockingQueue<StringMessage> queue;

    public UnlimitedQueuePipe() {
        queue = new LinkedBlockingQueue<>();
    }

    public void put(StringMessage StringMessage) throws InterruptedException {
        queue.put(StringMessage);
    }

    public StringMessage take() throws InterruptedException {
        return queue.take();
    }

    public StringMessage peek() {
        return queue.peek();
    }

    @Override
    public StringMessage poll(long timeout) throws InterruptedException {
        return queue.poll(timeout, TimeUnit.SECONDS);
    }
}
