package jcip.threadPools;

import java.util.concurrent.ThreadFactory;

/**
 * Created by zzt on 5/4/16.
 * <p>
 * Usage:
 */
public class MyThreadFactory implements ThreadFactory {

    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyAppThread(r, poolName);
    }
}
