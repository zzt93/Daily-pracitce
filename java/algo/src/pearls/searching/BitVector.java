package pearls.searching;

/**
 * Created by zzt on 9/7/16.
 * <p>
 * <h3></h3>
 */
public class BitVector {

    private static final int SHIFT = 5;
    private static final int MASK = 0x1f;
    public static final int SET = 1;
    private int max;
    private int capacity;
    private int[] x;

    public BitVector(int max, int capacity) {
        this.max = max;
        this.capacity = capacity;
        x = new int[capacity];
    }

    public void set(int i) {
        x[i >> SHIFT] |= (SET << (i & MASK));
    }

    public void clear(int i) {
        x[i >> SHIFT] &= ~(SET << (i & MASK));
    }

    public boolean test(int i) {
        return (x[i >> SHIFT] & (SET << (i & MASK))) == SET;
    }
}
