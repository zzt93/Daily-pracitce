package thread.performance.syncLockAtomic;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zzt on 4/14/16.
 * <p>
 * Usage:
 */
public class UseLock extends Contention {
    private Lock lock = new ReentrantLock();

    public UseLock(int s, int writeSize) {
        super(s, writeSize);
        id = "Lock";
    }

    @Override
    public void accumulate() {
        lock.lock();
        try {
            value += ThreadLocalRandom.current().nextInt(BOUND);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long read() {
        lock.lock();
        try {
            return value;
        } finally {
            lock.unlock();
        }
    }
}
