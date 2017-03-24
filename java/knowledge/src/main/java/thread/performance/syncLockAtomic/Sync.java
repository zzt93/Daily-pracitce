package thread.performance.syncLockAtomic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by zzt on 4/14/16.
 * <p>
 * Usage:
 */
public class Sync extends Contention {


    public Sync(int readSize, int writeSize) {
        super(readSize, writeSize);
        id = "Synchronized";
    }

    @Override
    public void accumulate() {
        synchronized (this) {
            value += ThreadLocalRandom.current().nextInt(Contention.BOUND);
        }
    }

    @Override
    public long read() {
        synchronized (this) {
            return value;
        }
    }
}