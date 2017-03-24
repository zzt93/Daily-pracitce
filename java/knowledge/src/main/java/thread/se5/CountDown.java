package thread.se5;

import java.util.concurrent.*;

/**
 * Created by zzt on 4/12/16.
 * <p>
 * Usage:
 */
class PartTask implements Runnable {
    private CountDownLatch latch;

    public PartTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        int i = ThreadLocalRandom.current().nextInt(200);
        try {
            TimeUnit.MILLISECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("do some work");
        latch.countDown();
    }
}

class WaitTask implements Runnable {
    private CountDownLatch latch;

    public WaitTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("wait finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class CountDown {
    public static void main(String[] args) {
        int size = 10;
        ExecutorService service = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            service.execute(new PartTask(latch));
        }
        for (int i = 0; i < 10; i++) {
            service.execute(new WaitTask(latch));
        }
        service.shutdown();
    }
}
