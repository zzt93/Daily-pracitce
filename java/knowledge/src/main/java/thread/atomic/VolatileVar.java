package thread.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 4/2/16.
 * <p>
 * Usage:
 */
public class VolatileVar implements Runnable {

    private volatile boolean stop = false;

    // without volatile the count has very large difference -- no cache
    private volatile int count = 0;

    void stop() {
        stop = true;
        System.out.println(count);
    }

    @Override
    public void run() {
        // without volatile this loop may never end
        while (!stop) {
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileVar volatileVar = new VolatileVar();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(volatileVar);
        Thread.sleep(10);
        volatileVar.stop();
        service.shutdown();
    }
}
