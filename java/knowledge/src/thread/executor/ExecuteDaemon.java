package thread.executor;

import thread.daemon.Daemon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by zzt on 4/5/16.
 * <p>
 * Usage:
 */
public class ExecuteDaemon {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool(r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        });
        for (int i = 0; i < 5; i++) {
            service.execute(new Daemon());
        }
        TimeUnit.MILLISECONDS.sleep(1000);
    }
}
