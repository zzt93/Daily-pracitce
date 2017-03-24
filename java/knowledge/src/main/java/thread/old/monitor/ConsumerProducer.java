package thread.old.monitor;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by zzt on 7/12/15.
 * <p>
 * Description: Using Monitor to solve consumer and Producer problem
 * using monitor
 */
public class ConsumerProducer<T> {
    public static final int CAPACITY = 10;
    public static final int THREAD_BOUND = 5;
    public static final int BOUND = 1000;
    private ArrayList<T> buffer;
    private Monitor full;
    private Monitor empty;

    private Supplier<T> producer;
    private Consumer<T> consumer;
    private static Random random = new Random(23);

    public ConsumerProducer(int capacity, Supplier<T> supplier, Consumer<T> consumer) {
        full = new Monitor();
        empty = new Monitor();
        full.shareResource(empty);

        buffer = new ArrayList<>(capacity);
        this.consumer = consumer;
        this.producer = supplier;
    }

    public void setProducer(Supplier<T> producer) {
        this.producer = producer;
    }

    public void setConsumer(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    public void producer() {
        while (true) {
            full.enter();// using empty is the same

            while (buffer.size() == CAPACITY) {
                full.waiton();
            }
            // handle buffer
            try {
                Thread.sleep(BOUND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buffer.add(producer.get());
            System.out.println("After producing:");
            buffer.forEach(integer -> System.out.print(":" + integer));
            System.out.println();
            // handle buffer end
            empty.notifyWith();

            full.leave();
        }
    }

    public void consumer() {
        while (true) {
            empty.enter();// using full is the same

            while (buffer.isEmpty()) {
                empty.waiton();
            }
            // handle buffer
            try {
                Thread.sleep(BOUND);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            consumer.accept(buffer.get(0));
            buffer.remove(0);
            System.out.println("After consuming:");
            buffer.forEach(integer -> System.out.print(":" + integer));
            System.out.println();
            // handle buffer end
            full.notifyWith();

            empty.leave();
        }
    }

    public void test() {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_BOUND);
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
        new ConsumerProducer<Integer>(10,
                () -> random.nextInt(10),
                i -> System.out.println("consuming " + i)
        ).test();
    }
}
