package thread.pv;

/**
 * Created by zzt on 6/30/15.
 * <p>
 * Description:
 * an emulation of Semaphore
 * and P&V
 * <p>
 * Semaphore is the abstraction of resource (A semaphore is
 * a resource that shared by all threads),
 * the value represent how many threads can share this resource
 * simultaneously
 * the list store the thread waiting for this resource
 * <p>
 * A single resource can be viewed from different perspectives
 * which will have multiple Semaphores: eg. Producer and Consumer {@link thread.pv.ProCon}<br>
 * The buffer can be used by only one thread at a time, so we need a mutex semaphore;<br>
 * the buffer can be viewed as k empty space, so a semaphore {@code empty = new Semaphore(k);}<br>
 * the buffer can be viewed as 0 full space, so a semaphore {@code full = new Semaphore(0);}<br>
 * <p>
 * The implementation of P&V in OS is often defend/surrounded by
 * {@code cli() -- close interrupt} and {@code sti() -- set interrupt enabled}
 * for the list of waiting thread and
 * counter of resource is also shared by many thread, ie the critical
 * section.
 * <p>
 * But using synchronized also fine because any critical sections' problem
 * is inconsistency of sharing and can be solved by stopping other threads
 * entering. Using lock and Closing interrupt work the same function.
 * And in os, P&V itself is lock, so using closing interrupt in order to avoid
 * 'recursive' lock.
 */
public class Semaphore {
    private int value;
//    private ArrayList<Thread> list = new ArrayList<>();

    public Semaphore(int value) {
        this.value = value;
    }

    public synchronized void P() {
        value--;
        if (value < 0) {
            /**
             * the following commented code is redundant and wrong
             *
             * Redundant: Every java object has its lock which maintain
             * the list of waiting thread
             *
             * Wrong: for the {@code currentThread.wait();} will just release the lock
             * of inner synchronized object -- here is currentThread -- but not lock
             * of {@code this}
             *
             * More about nested synchronized block:
             * {@link http://stackoverflow.com/questions/10365132/java-nested-synchronization-blocks}
             */
//            final Thread currentThread = Thread.currentThread();
//            list.add(currentThread);
//            synchronized (currentThread) {
//                try {
//                    currentThread.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void V() {
        value++;
        if (value <= 0) {
//            final Thread pop = list.get(0);
//            synchronized (pop) {
//                pop.notify();
//            }
            this.notify();
        }
    }
}
