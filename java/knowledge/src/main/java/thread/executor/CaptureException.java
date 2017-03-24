package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 4/6/16.
 * <p>
 * Usage:
 */
class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t.toString());
        e.printStackTrace();
    }
}

class MyThreadFactory implements java.util.concurrent.ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setUncaughtExceptionHandler(new MyExceptionHandler());
        return thread;
    }
}

public class CaptureException {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool(new MyThreadFactory());
        service.execute(() -> {
            System.out.println("thread start");
            System.out.println("throw exception");
            throw new RuntimeException("a test");
        });
    }
}
