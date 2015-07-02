package thread.monitor;

import thread.pv.Semaphore;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zzt on 6/30/15.
 * <p>
 * Description: an emulation of Monitor
 *
 *
 */
public class Monitor {
    private Semaphore mutex;
    private Semaphore notified;
    private int notifyCount;

    public Monitor() {
        mutex = new Semaphore(1);
        notified = new Semaphore(0);
        notifyCount = 0;
    }

    public void enter() {
        mutex.P();
    }

    public void leave() {
        if (notifyCount > 0) {
            notified.V();
        } else {
            mutex.V();
        }
    }

    public void waiton(Semaphore condition, AtomicInteger wantResource) {
        wantResource.getAndIncrement();
        leave();
        condition.P();
        wantResource.getAndDecrement();
    }

    public void notifyWith(Semaphore condition, AtomicInteger wantResource) {
        if (wantResource.get() > 0) {
            notifyCount++;
            condition.V();
            notified.P();
            notifyCount--;
        }
    }
}
