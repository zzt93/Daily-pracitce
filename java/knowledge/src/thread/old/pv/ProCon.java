package thread.old.pv;

import thread.old.monitor.ConsumerProducer;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by zzt on 7/15/15.
 * <p>
 * Description: An emulation of Producer and Consumer problem
 * using Semaphore
 */
public class ProCon<T> {
    public static final int BOUND = 1000;
    private Semaphore mutex = new Semaphore(1);
    private Semaphore full = new Semaphore(0);
    private Semaphore empty;
    private ArrayList<T> buffer;
    private Consumer<T> consumer;
    private Supplier<T> producer;
    private static Random random = new Random(23);

    public ProCon(int capacity) {
        empty = new Semaphore(capacity);
        buffer = new ArrayList<>(capacity);
    }

    public ProCon(int capacity, Consumer<T> consumer, Supplier<T> producer) {
        this(capacity);
        this.consumer = consumer;
        this.producer = producer;
    }

    public void setConsumer(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    public void setProducer(Supplier<T> producer) {
        this.producer = producer;
    }

    private void producer() {
        while (true) {
            empty.P();
            mutex.P();
            // handle buffer
            try {
                Thread.sleep(random.nextInt(BOUND));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buffer.add(producer.get());

            System.out.println("After producing:");
            buffer.forEach(i -> System.out.print(":" + i));
            System.out.println();
            // handle buffer end
            mutex.V();
            full.V();
        }
    }

    private void consumer() {
        while (true) {
            full.P();
            mutex.P();
            // handle buffer
            try {
                Thread.sleep(random.nextInt(BOUND));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            consumer.accept(buffer.get(0));
            buffer.remove(0);

            System.out.println("After consuming");
            buffer.forEach(i -> System.out.print(":" + i));
            System.out.println();
            // handle buffer end
            mutex.V();
            empty.V();
        }
    }

    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(ConsumerProducer.THREAD_BOUND);
        int pNum = random.nextInt(ConsumerProducer.THREAD_BOUND);
        int cNum = ConsumerProducer.THREAD_BOUND - pNum;
        for (int i = 0; i < pNum; i++) {
            executorService.submit(this::producer);
        }
        for (int i = 0; i < cNum; i++) {
            executorService.submit(this::consumer);
        }
    }

    public static void main(String[] args) {
        new ProCon<Integer>(10,
                integer -> System.out.println("consuming " + integer),
                () -> random.nextInt(10)
        ).test();
    }
}
