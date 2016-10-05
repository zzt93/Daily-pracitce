package thread.old.monitor;

import thread.old.pv.MySemaphore;

/**
 * Created by zzt on 7/14/15.
 * <p>
 * Description: An emulation of Monitor
 * <p>
 * In my implementation, a monitor is a way to inspect/protect a resource which has some state and
 * can be associated with a condition variable. Multiple monitors can protect a single resource
 * which have different condition variables: eg. {@link thread.old.monitor.ConsumerProducer} --
 * Producer and Consumer problem<br>
 * <p>
 * The buffer has a monitor to stop thread interleave;<br>
 * The buffer has a condition that all space are full and producer has to wait;<br>
 * The buffer has a condition that all space are empty and consumer has to wait;<br>
 */
public class Monitor {
    private final MySemaphore conditionV = new MySemaphore(0);
    /**
     * The protector of resource that the monitor represented
     */
    private MySemaphore mutex = new MySemaphore(1);

    public Monitor() {
    }

    public void enter() {
        mutex.P();
    }

    public void leave() {
        mutex.V();
    }

    public void waiton() {
        leave();
        conditionV.P();
        enter();
    }

    public void notifyWith() {
        conditionV.V();
    }

    public void shareResource(Monitor monitor) {
        monitor.mutex = mutex;
    }
}
