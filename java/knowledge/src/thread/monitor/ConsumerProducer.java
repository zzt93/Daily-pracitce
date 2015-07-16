package thread.monitor;

import thread.pv.Semaphore;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zzt on 7/12/15.
 * <p>
 * Description: Using Monitor to solve consumer and Producer problem
 * using monitor
 *
 */
public class ConsumerProducer {
    public static final int CAPACITY = 10;
    public static final int THREAD_BOUND = 5;
    ArrayList<Integer> buffer = new ArrayList<>(CAPACITY);
    private final Semaphore full = new Semaphore(0);
    Monitor bufferMonitor = new Monitor(full);

    public void producer() {
        while (true) {

        }
    }

    public void consumer() {
        while (true) {

        }
    }

    public void test() {
        Random random = new Random(23);
        ExecutorService executorService = Executors.newFixedThreadPool(random.nextInt(THREAD_BOUND));
        int pNum = random.nextInt(THREAD_BOUND);
        int cNum = THREAD_BOUND - pNum;
        for (int i = 0; i < pNum; i++) {
            executorService.submit(this::producer);
        }
        for (int i = 0; i < cNum; i++) {
            executorService.submit(this::consumer);
        }
    }

    public static void main(String[] args) {
        new ConsumerProducer().test();
    }
}
