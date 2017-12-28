package pearls;

import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by zzt on 10/9/16.
 * <p>
 * <h3></h3>
 * one-based array to implement max heap
 */
public class Heap {

    private static final int HEAP_START = 1;
    private final int maxSize;
    private int[] a;
    private int size = 0;

    public Heap(int maxSize) {
        this.maxSize = maxSize;
        this.a = new int[this.maxSize + HEAP_START];
        a[0] = Integer.MAX_VALUE;
    }

    private int parent(int i) {
        return i / 2;
    }

    private int lChild(int i) {
        return i * 2;
    }

    private int rChild(int i) {
        return i * 2 + 1;
    }

    private void shiftUp(int start) {
        int i = start;
        while (i > HEAP_START) {
            final int pi = parent(i);
            if (a[i] <= a[pi]) {
                break;
            } else {
                swap(i, pi);
                i = pi;
            }
        }
    }

    private void shiftDown(int start) {
        int i = start;
        while (true) {
            final int lChildI = lChild(i);
            final int rChildI = rChild(i);
            if (lChildI > size) {
                break;
            } else if (rChildI > size) {
                break;
            } else {
                int lc = a[lChildI];
                int rc = a[rChildI];
                int ti = lChildI;
                if (lc < rc) {
                    ti = rChildI;
                }
                if (a[ti] > a[i]) {
                    swap(i, ti);
                    i = ti;
                } else {
                    break;
                }
            }
        }
    }

    private void swap(int i, int pi) {
        int t = a[i];
        a[i] = a[pi];
        a[pi] = t;
    }

    public void insert(int t) {
        if (size >= maxSize) {
            throw new IllegalStateException("too many element");
        }
        size++;
        a[size] = t;
        shiftUp(size);
    }

    public int max() {
        return a[HEAP_START];
    }

    public int pop() {
        if (size == 0) {
            throw new IllegalStateException("empty heap");
        }
        int max = max();
        a[HEAP_START] = a[size];
        size--;
        shiftDown(HEAP_START);
        return max;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder().append("Heap(").append(size).append(")" +
                ":\n");
        int i = 1;
        int sum = i;
        for (int j = 1; j <= size; j++) {
            builder.append(a[j]).append(" ");
            if (j >= sum) {
                builder.append("\n");
                i *= 2;
                sum += i;
            }
        }
        return builder.toString();
    }

    public static Random random = new Random(12);

    public static void main(String[] args) {
        final Heap heap = new Heap(1000);
        for (int i = 0; i < 100; i++) {
            heap.insert(random.nextInt(1000));
            System.out.println(heap);
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(heap.pop());
        }
        assert Ordering.natural().reverse().isOrdered(list);
    }
}
