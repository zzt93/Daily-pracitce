package jcip.test;

/**
 * Created by zzt on 5/14/16.
 * <p>
 * <h3></h3>
 */
public class TestLeak {

    private static final int CAPACITY = 1000;
    private static final int THRESHOLD = 100;

    private class Big {
        private double[] data = new double[1000];
    }

    void test() throws InterruptedException {
        BoundedBuffer<Big> buffer = new BoundedBuffer<>(CAPACITY);
        long heapSize = freeHeapSize();
        for (int i = 0; i < CAPACITY; i++) {
            buffer.put(new Big());
        }
        for (int i = 0; i < CAPACITY; i++) {
            buffer.get();
        }
        long heapSize2 = freeHeapSize();
        assert heapSize - heapSize2 < THRESHOLD;
    }

    private long freeHeapSize() {
        return Runtime.getRuntime().freeMemory();
    }

    public static void main(String[] args) throws InterruptedException {
        new TestLeak().test();
    }
}
