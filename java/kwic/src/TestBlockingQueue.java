import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zzt on 2/1/16.
 * <p>
 * Usage:
 */
class Producer implements Runnable {
    private final BlockingQueue<String> queue;

    Producer(BlockingQueue<String> q) {
        queue = q;
    }

    public void run() {
        try {
            while (true) {
                queue.put(produce());
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    String produce() {
        return "producer";
    }
}

class Consumer implements Runnable {
    private final BlockingQueue<String> queue;

    Consumer(BlockingQueue<String> q) {
        queue = q;
    }

    public void run() {
        try {
            while (true) {
                consume(queue.take());
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    void consume(String x) {

    }
}

class Setup {
    public static void main(String[] args) {
        BlockingQueue<String> q = new ArrayBlockingQueue<String>(100);
        Producer p = new Producer(q);
        Consumer c1 = new Consumer(q);
        Consumer c2 = new Consumer(q);
        // consumer start first
        new Thread(c1).start();
        new Thread(c2).start();

        new Thread(p).start();
    }
}
