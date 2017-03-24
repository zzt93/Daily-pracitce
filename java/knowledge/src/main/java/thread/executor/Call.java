package thread.executor;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by zzt on 5/13/15.
 * <p>
 * Description:
 */
public class Call implements Callable<String> {

    private int id;

    public Call(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "id is " + id;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ArrayList<Future<String>> res = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            res.add(executorService.submit(new Call(i)));
        }
        for (Future<String> re : res) {
            try {
                System.out.println(re.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
