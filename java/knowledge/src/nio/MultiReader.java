package nio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 9/7/16.
 * <p>
 * <h3></h3>
 */
public class MultiReader {

    private static ExecutorService service = Executors.newFixedThreadPool(4);


    public MultiReader() {
        for (int i = 0; i < 4; i++) {
            service.submit(new ThreadReadJob(i));
        }
    }

    public static void main(String[] args) {
        new MultiReader();
    }
}
