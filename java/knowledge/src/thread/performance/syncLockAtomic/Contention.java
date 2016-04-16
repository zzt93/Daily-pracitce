package thread.performance.syncLockAtomic;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 4/14/16.
 * <p>
 * Usage:
 */
public abstract class Contention {
    static final int BOUND = 20;
    static long cycle = 50000;
    private static CyclicBarrier barrier = new CyclicBarrier(SyncVSLockVSAtomic.SUM + 1);
    static ExecutorService exec = Executors.newFixedThreadPool(SyncVSLockVSAtomic.SUM);

    private volatile int readSize;
    private volatile int writeSize;
    private volatile long duration;
    volatile String id;
    volatile long value;


    public Contention(int readSize, int writeSize) {
        this.readSize = readSize;
        this.writeSize = writeSize;
    }

    public abstract void accumulate();

    public abstract long read();

    private class Modifier implements Runnable {
        public void run() {
            for (long i = 0; i < cycle; i++) {
                accumulate();
            }
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class Reader implements Runnable {

        public void run() {
            for (long i = 0; i < cycle; i++) {
                value = read();
            }
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void timedTest() {
        long start = System.nanoTime();
        for (int i = 0; i < writeSize; i++) {
            exec.execute(new Modifier());
        }
        for (int i = 0; i < readSize; i++) {
            exec.execute(new Reader());
        }
        try {
            barrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        duration = System.nanoTime() - start;
        System.out.printf("%-13s: %13d\n", id, duration);
    }

    public static void
    report(Contention acc1, Contention acc2) {
        System.out.printf("%-22s: %.2f\n", acc1.id + "/" + acc2.id,
                (double) acc1.duration / (double) acc2.duration);
    }
}
