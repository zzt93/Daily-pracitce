package thread.performance.syncLockAtomic;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by zzt on 4/14/16.
 * <p>
 * Usage:
 */
public class BaseLine extends Contention {


    public BaseLine(int s, int writeSize) {
        super(s, writeSize);
        id = "BaseLine";
    }

    @Override
    public void accumulate() {
        value += ThreadLocalRandom.current().nextInt(Contention.BOUND);
    }

    @Override
    public long read() {
        return value;
    }
}
