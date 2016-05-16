package jcip.synchronizer;

import annotation.ThreadSafe;

/**
 * Created by zzt on 5/16/16.
 * <p>
 * <h3></h3>
 */
@ThreadSafe
public class WaitBoundedBuffer<V> extends BaseBoundedBuffer<V> {
    public WaitBoundedBuffer(int size) {
        super(size);
    }

    public synchronized void put(V v) throws InterruptedException {
        /**
         * consistent lock order
         */
        while (isFull()) {
            this.wait();
        }
        doPut(v);
        /**
         * Because multiple threads could be waiting on the same condition queue for
         * different condition predicates, using notify instead of notifyAll can be danger-
         * ous, primarily because single notification is prone to a problem akin to missed
         * signals.
         *
         * or use different lock and condition queue for empty wait -- optimization of
         * single notification
         */
        this.notifyAll();
    }

    /**
     * optimization of conditional notification
     * @param v
     * @throws InterruptedException
     */
    public synchronized void putOpt(V v) throws InterruptedException {
        while (isFull()) {
            wait();
        }
        final boolean empty = isEmpty();
        doPut(v);
        if (empty) {
            notifyAll();
        }
    }

    public synchronized V take() throws InterruptedException {
        while (isEmpty()) {
            this.wait();
        }
        final V v = doTake();
        this.notifyAll();
        return v;
    }
}
