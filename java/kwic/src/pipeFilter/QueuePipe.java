package pipeFilter;

import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 1/24/16.
 * <p>
 * Usage:
 */
public interface QueuePipe {
    void put(StringMessage s) throws InterruptedException;

    StringMessage take() throws InterruptedException;

    StringMessage peek();

    StringMessage poll(long timeout) throws InterruptedException;
}
