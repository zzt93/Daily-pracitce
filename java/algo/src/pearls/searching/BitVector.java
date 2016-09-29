package pearls.searching;

import java.util.List;

/**
 * Created by zzt on 9/7/16.
 * <p>
 * <h3></h3>
 */
public class BitVector {

    private static final int SHIFT = 5;
    private static final int MASK = 0x1f;
    private static final int SET = 1;
    private static final int BITS_PER_UNIT = 32;
    private int capacity;
    private int[] x;
    private int size = 0;

    public BitVector(int max, int capacity) {
        this.capacity = capacity;
        x = new int[capacity / 32 + 1];
        initX(x);
    }

    private void initX(int[] x) {
        for (int i = 0; i < capacity; i++) {
            clear(i);
        }
    }

    private void set(int i) {
        x[i >> SHIFT] |= (SET << (i & MASK));
    }

    private void clear(int i) {
        x[i >> SHIFT] &= ~(SET << (i & MASK));
    }

    private boolean test(int i) {
        return (x[i >> SHIFT] & (SET << (i & MASK))) == SET;
    }

    public void report(List<Integer> l) {
        for (int i = 0; i < capacity; i++) {
            if (test(i)) {
                l.add(i);
            }
        }
    }

    public void insert(int t) {
        if (test(t)) {
            return;
        }
        set(t);
        size++;
    }
}
