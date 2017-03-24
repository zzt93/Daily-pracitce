package jcip.synchronizer;

import annotation.ThreadSafe;
import net.jcip.annotations.GuardedBy;

/**
 * Created by zzt on 5/16/16.
 * <p>
 * <h3></h3>
 */
@ThreadSafe
public class ThreadGate {

    @GuardedBy("this")
    private int generation;
    @GuardedBy("this")
    private boolean isOpen;

    public synchronized void open() {
        isOpen = true;
        generation++;
        notifyAll();
    }

    public synchronized void close() {
        isOpen = false;
    }

    public synchronized void await() throws InterruptedException {
        final int current = this.generation;
        while (!isOpen && current == generation) {
            wait();
        }
    }
}
