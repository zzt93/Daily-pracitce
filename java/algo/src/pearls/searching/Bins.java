package pearls.searching;

/**
 * Created by zzt on 9/7/16.
 * <p>
 *     number range [0, maxval)
 *     HashMap like container
 * <h3></h3>
 */
public class Bins {

    private int capacity;
    private int maxval;
    private ListNode[] bin;
    private ListNode sentinel;
    private int loadFactor = 2;

    public Bins(int maxval) {
        this.capacity = getIndex(maxval);
        this.maxval = maxval;
        bin = new ListNode[capacity];
        sentinel = new ListNode(capacity, null);
        initBin(bin);
    }

    private int getIndex(int maxval) {
        return maxval / loadFactor;
    }

    private void initBin(ListNode[] bin) {
        for (int i = 0; i < bin.length; i++) {
            bin[i] = sentinel;
        }
    }

    public void insert(int t) {

    }

    public void recursiveInsert(int t) {

    }
}
