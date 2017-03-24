package thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 4/7/16.
 * <p>
 * Usage:
 * A second way to prevent tasks from colliding over shared resources is to eliminate the
 * sharing of variables.
 */
class TaskWithCount implements Runnable {

    private int id;

    public TaskWithCount(int id) {
        this.id = id;
    }

    public String toString() {
        return "#" + id + ": " +
                ThreadLocalStorage.get();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(this);
            ThreadLocalStorage.increment();
            Thread.yield();
        }
    }
}

public class ThreadLocalStorage {

    private static ThreadLocal<Integer> uid
            = new ThreadLocal<Integer>() {
        private Random rand = new Random(47);

        protected synchronized Integer initialValue() {
            return rand.nextInt(10000);
        }
    };

    public static int get() {
        return uid.get();
    }

    public static void increment() {
        uid.set(uid.get() + 1);
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new TaskWithCount(i));
        TimeUnit.MILLISECONDS.sleep(30); // Run for a while
        // All Accessors will quit
        exec.shutdownNow();
    }
}
