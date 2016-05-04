package jcip.threadPools;

import annotation.ThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * Created by zzt on 5/4/16.
 * <p>
 * Usage:
 */
@ThreadSafe
public class BoundedExec {
    private final ExecutorService service;
    private final Semaphore semaphore;

    public BoundedExec(ExecutorService service, int bound) {
        this.service = service;
        this.semaphore = new Semaphore(bound);
    }

    public void submitTask(Runnable runnable) throws InterruptedException {
        semaphore.acquire();
        try {
            service.execute(() -> {
                try {
                    runnable.run();
                } finally {
                    semaphore.release();
                }
            });
        } catch (RejectedExecutionException e){
            semaphore.release();
        }
    }
}
