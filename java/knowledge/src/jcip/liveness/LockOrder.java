package jcip.liveness;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 5/7/16.
 * <h3>Problem</h3>
 * <p>not aquire the lock in the right order</p>
 * <h3>Tool</h3>:
 * Thread dumps also include locking information, such as which locks are
 * held by each thread, in which stack frame they were acquired, and which lock a
 * blocked thread is waiting to acquire. 4 Before generating a thread dump, the JVM
 * searches the is-waiting-for graph for cycles to find deadlocks. If it finds one, it
 * includes deadlock information identifying which locks and threads are involved,
 * and where in the program the offending lock acquisitions are.
 */
public class LockOrder {
    private final Object left = new Object();
    private final Object right = new Object();

    public void left() {
        synchronized (left) {
            synchronized (right) {
                doSome();
            }
        }
    }

    private void doSome() {

    }

    public void right() {
        synchronized (right) {
            synchronized (left) {
                doSome();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final LockOrder lockOrder = new LockOrder();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                service.execute(lockOrder::left);
            } else {
                service.execute(lockOrder::right);
            }
        }
        service.shutdown();
    }
}
