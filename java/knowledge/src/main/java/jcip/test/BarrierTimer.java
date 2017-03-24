package jcip.test;

/**
 * Created by zzt on 5/14/16.
 * <p>
 * <h3></h3>
 */
public class BarrierTimer implements Runnable {
    private boolean start;
    private long startTime, endTime;

    public synchronized void clear() {
        start = false;
    }

    public synchronized long getTime() {
        return endTime - startTime;
    }

    @Override
    public synchronized void run() {
        if (start) {
            startTime = System.nanoTime();
            start = true;
        } else {
            endTime = System.nanoTime();
        }
    }
}
