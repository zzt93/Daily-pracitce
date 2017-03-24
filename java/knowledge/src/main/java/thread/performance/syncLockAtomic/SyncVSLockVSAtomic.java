package thread.performance.syncLockAtomic;

/**
 * Created by zzt on 4/14/16.
 * <p>
 * First and foremost, we will only see
 * the true performance difference if the mutexes are under contention, so there must be
 * multiple tasks trying to access the mutexed code sections. In the above example, each mutex
 * is tested by the single main( ) thread, in isolation.
 * <p></p>
 * Secondly, it’s possible that the compiler can perform special optimizations when it sees the
 * synchronized keyword, and perhaps even notice that this program is single-threaded. The
 * compiler might even identify that the counter is simply being incremented a fixed number
 * of times, and just pre-calculate the result. Different compilers and runtime systems vary, so
 * it’s hard to know exactly what will happen, but we need to prevent the possibility that the
 * compiler can predict the outcome.
 */
public class SyncVSLockVSAtomic {

    public static final int SUM = 10;

    private static void test(int readSize, int writeSize) {
        BaseLine baseLine = new BaseLine(readSize, writeSize);
        Sync sync = new Sync(readSize, writeSize);
        UseLock lock = new UseLock(readSize, writeSize);
        Atomic atomic = new Atomic(readSize, writeSize);
        UseRWLock rwLock = new UseRWLock(readSize, writeSize);
        System.out.println("============================");
        System.out.printf("%-12s : %13d\n", "Cycles", Contention.cycle);
        System.out.printf("Read:%-7d - %10s:%2d\n", readSize, "Write", writeSize);
        baseLine.timedTest();
        sync.timedTest();
        lock.timedTest();
        atomic.timedTest();
        rwLock.timedTest();
        Contention.report(sync, baseLine);
        Contention.report(lock, baseLine);
        Contention.report(atomic, baseLine);
        Contention.report(rwLock, baseLine);
        Contention.report(sync, lock);
        Contention.report(sync, atomic);
        Contention.report(lock, atomic);
    }

    public static void main(String[] args) {
        int iterations = 5; // Default
        if (args.length > 0) {
            iterations = new Integer(args[0]);
        }
        // The first time fills the thread pool:
        System.out.println("Warmup");
        new BaseLine(5, 5).timedTest();
        // Now the initial test doesn’t include the cost
        // of starting the threads for the first time.
        // Produce multiple data points:
        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < SUM - 1; j++) {
                test(j + 1, SUM - j - 1);
            }
            Contention.cycle *= 2;
        }
        Contention.exec.shutdown();
    }
}
