package thread.monitor;

import thread.pv.Semaphore;

/**
 * Created by zzt on 7/14/15.
 * <p>
 * Description: An emulation of Monitor
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
