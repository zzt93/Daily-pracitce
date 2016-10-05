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

    /**
     * @param objects objects that to be notified
     *
     * @return index of object that is first notified
     */
    public static int waitForAny(Object... objects) {
        Semaphore semaphore = new Semaphore(objects.length);
//        service.submit()
        return 0;
    }

    public static void waitForAll(Object... objects) throws InterruptedException {
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

    public static void waitForAllInvoke(Object... objects) throws InterruptedException {
        waitForInvoke((tasks) -> {
            try {
                service.invokeAll(tasks);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, objects);
    }

    public static void waitForAnyInvoke(Object... objects) throws InterruptedException {
        waitForInvoke((tasks) -> {
            try {
                service.invokeAny(tasks);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }, objects);
    }

    public static void waitForInvoke(Consumer<List<Callable<Void>>> consumer, Object[] objects) throws InterruptedException {
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
