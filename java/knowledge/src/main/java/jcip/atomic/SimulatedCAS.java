package jcip.atomic;

import annotation.ThreadSafe;
import net.jcip.annotations.GuardedBy;

/**
 * Created by zzt on 5/19/16.
 * <p>
 * <h3></h3>
 */
@ThreadSafe
public class SimulatedCAS {

    @GuardedBy("this")
    private int value;

    private synchronized boolean compareAndSwap(int expectedValue, int newValue) {
        int old = value;
        if (old == expectedValue) {
            value = newValue;
            return true;
        }
        return false;
    }

    public synchronized int get() {
        return value;
    }

    public synchronized boolean compareAndSet(int newV) {
        return compareAndSwap(value, newV);
    }
}
