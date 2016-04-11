package thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zzt on 4/11/16.
 * <p>
 * Usage:
 */
class Consumer implements Runnable {

    private Lock lock;
    private Condition condition;

    public Consumer(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            lock.lock();
            try {
                while (!Cooperation.isReady) {
                    condition.await();
                }
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println("consume one");
                Cooperation.isReady = false;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            } finally {
                lock.unlock();
            }
        }
    }
}

class Producer implements Runnable {

    private Lock lock;
    private Condition condition;

    public Producer(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            lock.lock();
            try {
                while (Cooperation.isReady) {
                    condition.await();
                }
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println("produce one");
                Cooperation.isReady = true;
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            } finally {
                lock.unlock();
            }
        }
    }
}

public class Cooperation {
    static volatile boolean isReady = false;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        service.execute(new Consumer(lock, condition));
        service.execute(new Producer(lock, condition));
        TimeUnit.SECONDS.sleep(2);
        service.shutdownNow();
    }
}
