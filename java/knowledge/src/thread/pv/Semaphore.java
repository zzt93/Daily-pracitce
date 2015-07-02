package thread.pv;

import java.util.ArrayList;

/**
 * Created by zzt on 6/30/15.
 * <p>
 * Description: an emulation of Semaphore
 * and P&V
 *
 * Semaphore is the abstraction of resource (A semaphore is
 * a resource that shared by all threads),
 * the value represent how many threads can share this resource
 * simultaneously
 * the list store the thread waiting for it
 *
 */
public class Semaphore {
    private int value;
    private ArrayList<Thread> list = new ArrayList<>();

    public Semaphore(int value) {
        this.value = value;
    }

    public synchronized void P() {
        value--;
        if (value < 0) {
            final Thread currentThread = Thread.currentThread();
            list.add(currentThread);
            synchronized (currentThread) {
                try {
                    currentThread.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void V() {
        value++;
        if (value <= 0) {
            final Thread pop = list.get(0);
            synchronized (pop) {
                pop.notify();
            }
        }
    }
}
