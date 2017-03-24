package jcip.cancel.crawler;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 5/2/16.
 * <p>
 * Usage: fix the limitation of shutDownNow(), can retrieve started but not finished job
 * <p>
 * TrackingExecutor has an unavoidable race condition that could make it yield
 * false positives: tasks that are identified as cancelled but actually completed. This
 * arises because the thread pool could be shut down between when the last instruc-
 * tion of the task executes and when the pool records the task as complete. This is
 * not a problem if tasks are idempotent (if performing them twice has the same ef-
 * fect as performing them once), as they typically are in a web crawler. Otherwise,
 * the application retrieving the cancelled tasks must be aware of this risk and be
 * prepared to deal with false positives.
 * </p>
 */
public class TrackingExecutor extends AbstractExecutorService {
    private ExecutorService exec;
    private Set<Runnable> canceled
            = Collections.synchronizedSet(new HashSet<>());

    public TrackingExecutor(ExecutorService exec) {
        this.exec = exec;
    }

    @Override
    public void shutdown() {
        exec.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return exec.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return exec.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return exec.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return exec.awaitTermination(timeout, unit);
    }

    @Override
    public void execute(Runnable command) {
        exec.execute(() -> {
            try {
                command.run();
            } finally {
                if (isShutdown()
                        && Thread.currentThread().isInterrupted()) {
                    canceled.add(command);
                }
            }
        });
    }

    public List<Runnable> canceledTask() {
        return new ArrayList<>(canceled);
    }
}
