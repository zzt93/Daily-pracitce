package thread.performance.container;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by zzt on 4/15/16.
 * <p>
 * Usage:
 */
public abstract class Tester<C> {
    public static final int RANDOM_NUMBER_BOUND = 66;
    public static final int RANDOM_NUMBER_ORIGIN = 0;
    private static int testResp = 10;
    static int testCycles = 1000;
    static int containerSize = 1000;

    abstract C containerInitializer();

    abstract void startReaderAndWrite();

    C testContainer;
    private String testId;
    int nReaders;
    int nWriters;
    volatile long readResult = 0;
    volatile long readTime = 0;
    volatile long writeTime = 0;
    private CountDownLatch endLatch;
    static ExecutorService exec =
            Executors.newCachedThreadPool();
    Integer[] writeData;

    Tester(String testId, int nReaders, int nWriters) {
        this.testId = testId + " " +
                nReaders + "r " + nWriters + "w";
        this.nReaders = nReaders;
        this.nWriters = nWriters;
        writeData = new Random(47)
                .ints(containerSize)
                .boxed()
                .collect(Collectors.toList())
                .toArray(new Integer[0]);
        for (int i = 0; i < testResp; i++) {
            runTest(i);
            readTime = 0;
            writeTime = 0;
        }
    }

    private void runTest(int i) {
        endLatch = new CountDownLatch(nReaders + nWriters);
        testContainer = containerInitializer();
        startReaderAndWrite();
        try {
            endLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (i == testResp - 1) {
            System.out.printf("%-27s %14d %14d\n",
                    testId, readTime, writeTime);
            if (readTime != 0 && writeTime != 0) {
                System.out.printf("%-27s %14d\n",
                        "readTime + writeTime = ", readTime + writeTime);
            }
        }
    }

    abstract class TestTask implements Runnable {
        abstract void test();

        abstract void putResults();

        long duration;

        @Override
        public void run() {
            long startTime = System.nanoTime();
            test();
            duration = System.nanoTime() - startTime;
            synchronized (Tester.this) {
                putResults();
            }
            endLatch.countDown();
        }
    }

    public static void initMain(String[] args) {
        if (args.length > 0) {
            testResp = new Integer(args[0]);
        }
        if (args.length > 1) {
            testCycles = new Integer(args[1]);
        }
        if (args.length > 2) {
            containerSize = new Integer(args[2]);
        }
        System.out.printf("%-27s %14s %14s\n",
                "Type", "Read time", "Write time");
    }
}
