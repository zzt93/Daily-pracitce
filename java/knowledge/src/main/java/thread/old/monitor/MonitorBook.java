package thread.old.monitor;

import thread.old.pv.MySemaphore;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zzt on 6/30/15.
 * <p>
 * Description: an emulation of Monitor
 * <p>
 * Strange implementation on the book, doesn't make sense for me
 */
public class MonitorBook {
    private MySemaphore mutex;
    private MySemaphore notified;
    private int notifyCount;

    public MonitorBook(MySemaphore conditionV) {
        mutex = new MySemaphore(1);
        notified = new MySemaphore(0);
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

    public void waiton(MySemaphore condition, AtomicInteger wantResource) {
        wantResource.getAndIncrement();
        leave();
        condition.P();
        wantResource.getAndDecrement();
    }

    public void notifyWith(MySemaphore condition, AtomicInteger wantResource) {
        if (wantResource.get() > 0) {
            notifyCount++;
            condition.V();
            notified.P();
            notifyCount--;
        }
    }
}
