package thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zzt on 4/6/16.
 * <p>
 * Usage:
 * Right after the call to lock( ), you must place
 * a try-finally statement with unlock( ) in the finally clause—this is the only way to
 * guarantee that the lock is always released.
 * Note that the return statement must occur inside
 * the try clause to ensure that the unlock( ) doesn’t happen too early and expose the
 * data to a second task
 */
public class Explicit {
    private Lock lock = new ReentrantLock();
    private int count = 0;

    public int inc() {
        lock.lock();
        try {
            count++;
            return count;
        } finally {
            lock.unlock();
        }
    }
}
