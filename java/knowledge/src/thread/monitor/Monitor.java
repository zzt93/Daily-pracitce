package thread.monitor;

import thread.pv.Semaphore;

/**
 * Created by zzt on 7/14/15.
 * <p>
 * Description: An emulation of Monitor
 * <p>
 * A monitor is used to protect a resource which can be
 * associated with multiple condition variables: eg. {@link thread.monitor.ConsumerProducer} --
 * Producer and Consumer problem<br></>
 * <p>
 * The buffer has a monitor to stop thread interleave;<br>
 * The buffer has a condition that all space are full and producer has to wait;<br>
 * The buffer has a condition that all space are empty and consumer has to wait;<br>
 */
public class Monitor {
    private final Semaphore conditionV;
    /**
     * The protector of resource that the monitor represented
     */
    private Semaphore mutex = new Semaphore(1);

    public Monitor(Semaphore conditionV) {
        mutex = new Semaphore(1);
        this.conditionV = conditionV;
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
    }

    public void notifyWith() {
        conditionV.V();
    }
}
