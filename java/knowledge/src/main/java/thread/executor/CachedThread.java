package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 5/13/15.
 * <p>
 * Description:
 */
public class CachedThread {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executor.execute(new Work());
        }
        executor.shutdown();
    }

}
