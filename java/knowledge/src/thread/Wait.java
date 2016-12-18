package thread;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

/**
 * Created by zzt on 10/5/16.
 * <p>
 * <h3></h3>
 */
public class Wait {

    private static ExecutorService service = Executors.newCachedThreadPool();
    private final Object[] objects;

    public Wait(Object... objects) {
        this.objects = objects;
    }

    /**
     * @return index of object that is first notified
     */
    public synchronized int waitForAnySemaphore() throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        for (int i = 0; i < objects.length; i++) {
            int finalI = i;
            service.submit(() -> {
                synchronized (objects[finalI]) {
                    objects[finalI].wait();
                }
                semaphore.release();
                return null;
            });
        }
        semaphore.acquire();
        // cancel other waiting to release thread
        for (Object object : objects) {
            notify(object);
        }
        return 0;
    }

    public synchronized int waitForAnyCountDown() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < objects.length; i++) {
            int finalI = i;
            service.submit(() -> {
                synchronized (objects[finalI]) {
                    objects[finalI].wait();
                }
                latch.countDown();
                return null;
            });
        }
        latch.await();
        // cancel other waiting to release thread
        for (Object object : objects) {
            notify(object);
        }
        return 0;
    }

    public synchronized void notify(Object o) {
        synchronized (o) {
            o.notify();
        }
    }

    public synchronized void waitForAll() throws InterruptedException {
        List<Future<Void>> res = new ArrayList<>(objects.length);
        for (Object object : objects) {
            res.add(service.submit(() -> {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }));
        }
        for (Future<Void> re : res) {
            try {
                re.get();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void waitForAllInvoke() throws InterruptedException {
        waitForInvoke((tasks) -> {
            try {
                service.invokeAll(tasks);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, objects);
    }

    public synchronized void waitForAnyInvoke() throws InterruptedException {
        waitForInvoke((tasks) -> {
            try {
                service.invokeAny(tasks);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }, objects);
    }

    private void waitForInvoke(Consumer<List<Callable<Void>>> consumer, Object[] objects) throws InterruptedException {
        List<Callable<Void>> jobs = new ArrayList<>(objects.length);
        for (Object object : objects) {
            jobs.add(() -> {
                synchronized (object) {
                    object.wait();
                }
                return null;
            });
        }
        consumer.accept(jobs);
    }


}
