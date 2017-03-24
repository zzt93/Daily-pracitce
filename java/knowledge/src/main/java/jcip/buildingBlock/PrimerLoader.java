package jcip.buildingBlock;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by zzt on 4/25/16.
 * <p>
 * Usage:
 */
public class PrimerLoader {
    private int limit;

    public PrimerLoader(int limit) {
        this.limit = limit;
    }

    private FutureTask<ArrayList<Integer>> futureTask =
            new FutureTask<>(() -> {
                ArrayList<Integer> res = new ArrayList<>();
                res.add(2);
                res.add(3);
                for (int i = 5; i < limit; i += 2) {
                    if (isPrime(i)) {
                        res.add(i);
                    }
                }
                return res;
            });

    private boolean isPrime(int n) {
        if ((n & 1) == 0) {
            return false;
        }
        for (int j = 3; j * j <= n; j += 2) {
            if (n % j == 0) {
                return false;
            }
        }
        return true;
    }

    private Thread thread = new Thread(futureTask);

    public void start() {
        thread.start();
    }

    public ArrayList<Integer> get() throws ExecutionException, InterruptedException {
        return futureTask.get();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        PrimerLoader primerLoader = new PrimerLoader(100000);
        primerLoader.start();
        Thread.sleep(1000);
        System.out.println(primerLoader.get());
    }
}
