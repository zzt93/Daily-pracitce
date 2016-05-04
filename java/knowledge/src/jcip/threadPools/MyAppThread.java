package jcip.threadPools;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zzt on 5/4/16.
 * <p>
 * Usage:
 */
public class MyAppThread extends Thread {

    private static final String DEFAULT_APP_NAME = "app";
    private static final AtomicInteger alive = new AtomicInteger();
    private static final AtomicInteger count = new AtomicInteger();
    private static final Logger logger = Logger.getAnonymousLogger();
    private static volatile boolean debugLevel = false;

    public MyAppThread(Runnable target) {
        super(target, DEFAULT_APP_NAME);
    }

    public MyAppThread(Runnable r, String poolName) {
        super(r, poolName + "-" + count.incrementAndGet());
        setUncaughtExceptionHandler((t, e) -> {
            logger.log(Level.SEVERE, "Uncaught exception in " + t.getName(), e.toString());
        });
    }

    @Override
    public void run() {
        boolean debug = debugLevel;
        if (debug) {
            logger.log(Level.FINE, getName() + " start");
        }
        alive.incrementAndGet();
        try {
            super.run();
        } finally {
            alive.decrementAndGet();
        }
        if (debug) {
            logger.log(Level.FINE, getName() + " finished");
        }
    }

    public int count() {
        return count.get();
    }

    public int alive() {
        return alive.get();
    }

    public static void setDebugLevel(boolean debugLevel) {
        MyAppThread.debugLevel = debugLevel;
    }
}
