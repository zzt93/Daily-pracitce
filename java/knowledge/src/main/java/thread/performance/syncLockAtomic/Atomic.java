package thread.performance.syncLockAtomic;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zzt on 4/14/16.
 * <p>
 * Usage:
 */
public class Atomic extends Contention {
    private final AtomicInteger integer = new AtomicInteger();

    public Atomic(int s, int writeSize) {
        super(s, writeSize);
        id = "Atomic";
    }

    @Override
    public void accumulate() {
        int delta = ThreadLocalRandom.current().nextInt(Contention.BOUND);
        integer.addAndGet(delta);
    }

    @Override
    public long read() {
        return integer.get();
    }
}
