package jcip.synchronizer;

import annotation.ThreadSafe;
import net.jcip.annotations.GuardedBy;

/**
 * Created by zzt on 5/16/16.
 * <p>
 * <h3></h3>
 */
@ThreadSafe
public class BaseBoundedBuffer<V> {

    @GuardedBy("this")
    private final V[] buf;
    @GuardedBy("this")
    private int head;
    @GuardedBy("this")
    private int tail;
    @GuardedBy("this")
    private int count;

    public BaseBoundedBuffer(int size) {
        buf = (V[]) new Object[size];
    }

    public synchronized boolean isFull() {
        return count == buf.length;
    }

    public synchronized boolean isEmpty() {
        return count == 0;
    }

    protected final synchronized void doPut(V v) {
        count++;
        buf[tail++] = v;
        if (tail == buf.length) {
            tail = 0;
        }
    }

    protected final synchronized V doTake() {
        count--;
        final V v = buf[head];
        buf[head] = null;
        if (++head == buf.length) {
            head = 0;
        }
        return v;
    }
}
