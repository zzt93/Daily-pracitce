package thread.performance.syncLockAtomic;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by zzt on 4/16/16.
 * <p>
 * Usage:
 */
public class UseRWLock extends Contention{
    private ReentrantReadWriteLock lockRW = new ReentrantReadWriteLock();

    public UseRWLock(int s, int writeSize) {
        super(s, writeSize);
        id = "RWLock";
    }

    @Override
    public void accumulate() {
        ReentrantReadWriteLock.WriteLock writeLock = lockRW.writeLock();
        writeLock.lock();
        try {
            value += ThreadLocalRandom.current().nextInt(BOUND);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public long read() {
        ReentrantReadWriteLock.ReadLock readLock = lockRW.readLock();
        readLock.lock();
        try {
            return value;
        } finally {
            readLock.unlock();
        }
    }
}
