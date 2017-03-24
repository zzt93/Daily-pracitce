package jcip.synchronizer;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zzt on 5/17/16.
 * <p>
 * <h3></h3>
 */
@ThreadSafe
public class ConditionBuffer<V> extends BaseBoundedBuffer<V> {
    private final ReentrantLock lock = new ReentrantLock();
    @GuardedBy("lock")
    private final Condition empty = lock.newCondition();
    @GuardedBy("lock")
    private final Condition full = lock.newCondition();

    public ConditionBuffer(int size) {
        super(size);
    }

    public void put(V v) throws InterruptedException {
        lock.lock();
        try {
            if (isFull()) {
                full.await();
            }
            doPut(v);
            empty.signal();
        } finally {
            lock.unlock();
        }
    }

    public V take() throws InterruptedException {
        lock.lock();
        try {
            if (isEmpty()) {
                empty.await();
            }
            final V v = doTake();
            full.signal();
            return v;
        } finally {
            lock.unlock();
        }
    }
}
