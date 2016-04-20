package so_test;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentMapIteration {
    private final Map<String, String> map = new ConcurrentHashMap<String, String>();
    private final Iterator<Map.Entry<String, String>> iterator;

    private final static int MAP_SIZE = 10000;

    public static void main(String[] args) throws InterruptedException {
        new ConcurrentMapIteration().run();
    }

    public ConcurrentMapIteration() {
        for (int i = 0; i < MAP_SIZE; i++) {
            map.put("key" + i, UUID.randomUUID().toString());
        }
        this.iterator = this.map.entrySet().iterator();
    }

    private final ExecutorService executor = Executors.newCachedThreadPool();

    private final class Accessor implements Runnable {
        private final Iterator<Map.Entry<String, String>> iterator;

        public Accessor(Iterator<Map.Entry<String, String>> iterator) {
            this.iterator = iterator;
        }

        @Override
        public void run() {
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                try {
                    String st = Thread.currentThread().getName() + " - [" + entry.getKey() + ", " + entry.getValue() + ']';
                    System.out.println(st);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private final class Mutator implements Runnable {

        private final Map<String, String> map;
        private final Random random = new Random();

        public Mutator(Map<String, String> map) {
            this.map = map;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                this.map.remove("key" + random.nextInt(MAP_SIZE));
                this.map.put("key" + random.nextInt(MAP_SIZE), UUID.randomUUID().toString());
            }
        }
    }

    private void run() throws InterruptedException {
        Accessor a1 = new Accessor(this.iterator);
        Accessor a2 = new Accessor(this.iterator);
        Mutator m = new Mutator(this.map);

        executor.execute(a1);
//        executor.execute(m);
        executor.execute(a2);
        executor.shutdown();
        executor.awaitTermination(3, TimeUnit.SECONDS);
    }
}