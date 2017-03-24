package jcip.threadPools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * Created by zzt on 5/5/16.
 * <p>
 * Usage:
 */
public class TimingExecutor extends ThreadPoolExecutor {
    private final AtomicLong num = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();
    private final ThreadLocal<Long> startTime = new ThreadLocal<Long>() {
        @Override
        protected Long initialValue() {
            return 0L;
        }
    };
    private final Logger logger = Logger.getLogger("Timing Executor");

    public TimingExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
//        ConsoleHandler handler = new ConsoleHandler();
//        handler.setFormatter(new SimpleFormatter());
//        handler.setLevel(Level.ALL);
//        logger.addHandler(handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        assert t == Thread.currentThread();
        logger.info(String.format("Thread: %s, run: %s", t, r));
        startTime.set(System.nanoTime());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        final Long start = startTime.get();
        long time = System.nanoTime() - start;
        logger.info(String.format("Thread %s, time: %d", Thread.currentThread(), time));
        num.incrementAndGet();
        totalTime.addAndGet(time);
    }

    @Override
    protected void terminated() {
        try {
            logger.info(String.format("Terminated: avg time=%dns",
                    totalTime.get() / num.get()));
        } finally {
            super.terminated();
        }
    }

    public static void main(String[] args) {
        final TimingExecutor timingExecutor = new TimingExecutor(4, 10, 60L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        timingExecutor.submit(() -> false);
//        timingExecutor.submit(() -> {
//            System.out.println("...");
//        });
        timingExecutor.shutdown();
    }
}
