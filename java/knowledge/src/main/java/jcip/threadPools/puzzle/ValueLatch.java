package jcip.threadPools.puzzle;

import annotation.GuardedBy;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zzt on 5/6/16.
 * <p>
 * Usage:
 */
public class ValueLatch<T> {
    private final CountDownLatch latch = new CountDownLatch(1);
    @GuardedBy(type = ValueLatch.class, varName = "this")
    private T solution;

    public synchronized void setSolution(T solution) {
        latch.countDown();
        this.solution = solution;
    }

    public T getSolution() throws InterruptedException {
        latch.await();
        synchronized (this) {
            return solution;
        }
    }

    public boolean isSet() {
        return latch.getCount() == 0;
    }
}
