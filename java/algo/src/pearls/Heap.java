package pearls;

import competition.utility.ArrayUtility;

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

    private static final int HEAD_START = 1;
    private int[] a;
    private int size = 0;

    public Heap(int maxSize) {
        this.a = new int[maxSize + HEAD_START];
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

    private void shiftUp() {
        int i = size;
        while (i >= HEAD_START) {
            final int pi = parent(i);
            if (a[i] <= a[pi]) {
                break;
            } else {
                swap(i, pi);
                i = pi;
            }
        }
    }

    private void shiftDown() {
        int i = HEAD_START;
        while (i <= size) {
            final int lChildI = lChild(i);
            final int rChildI = rChild(i);
            if (lChildI >= size) {
                break;
            } else if (rChildI >= size) {
                break;
            } else {
                int lc = a[lChildI];
                int rc = a[rChildI];
                if (lc > a[i]) {
                    swap(i, lChildI);
                    i = lChildI;
                } else if (rc > a[i]) {
                    swap(i, rChildI);
                    i = rChildI;
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
        size++;
        a[size] = t;
        shiftUp();
    }

    public int max() {
        return a[HEAD_START];
    }

    public int pop() {
        int max = max();
        a[HEAD_START] = a[size];
        size--;
        shiftDown();
        return max;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder().append("Heap(").append(size).append("):\n");
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
        assert ArrayUtility.isSorted(list, true);
    }
}
