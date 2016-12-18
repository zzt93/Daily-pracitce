package thread;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    public synchronized int waitForAny() {
        Semaphore semaphore = new Semaphore(objects.length);
        return 0;
    }

    public synchronized void notify(Object o) {

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

    public void waitForAllInvoke() throws InterruptedException {
        waitForInvoke((tasks) -> {
            try {
                service.invokeAll(tasks);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, objects);
    }

    public void waitForAnyInvoke() throws InterruptedException {
        waitForInvoke((tasks) -> {
            try {
                service.invokeAny(tasks);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }, objects);
    }

    public void waitForInvoke(Consumer<List<Callable<Void>>> consumer, Object[] objects) throws InterruptedException {
        List<Callable<Void>> jobs = new ArrayList<>(objects.length);
        for (Object object : objects) {
            jobs.add(() -> {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            });
        }
        service.invokeAll(jobs);
    }


}
