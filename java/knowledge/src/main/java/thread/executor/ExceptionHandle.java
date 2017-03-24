package thread.executor;

import java.util.concurrent.*;

/**
 * Created by zzt on 1/1/17.
 * <p>
 * <h3></h3>
 */
public class ExceptionHandle {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(() -> {
            Thread.sleep(1);
            return null;
        });
        service.submit(() -> {
            //            Thread.sleep(1);
        });
        service.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                Thread.sleep(1);
                return null;
            }
        });
        service.submit(new Runnable() {
            @Override
            public void run() {
                //                Thread.sleep(1);
            }
        });
        final Future<Object> submit = service.submit(() -> {
            Thread.sleep(1);
            throw new IllegalArgumentException("throw it");
        });
        try {
            submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
            e.getCause().printStackTrace();
        }
    }
}
