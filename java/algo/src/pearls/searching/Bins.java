package pearls.searching;

/**
 * Created by zzt on 9/7/16.
 * <p>
 * number range [0, maxval)
 * HashMap like container for Integer
 * <h3></h3>
 */
public class Bins {

    private int capacity;
    private int maxval;
    private ListNode[] bin;
    private ListNode sentinel;
    private int loadFactor = 2;

    public Bins(int maxval) {
        this.capacity = getIndex(maxval) + 1;
        this.maxval = maxval;
        bin = new ListNode[capacity];
        sentinel = new ListNode(maxval, null);
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
        ListNode next = getBin(t);
        ListNode last = null;
        while (next.getVal() < t) {
            last = next;
            next = next.getNext();
        }
        final ListNode node = new ListNode(t, next);
        if (last == null) {
            setBin(node);
        } else {
            last.setNext(node);
        }
    }

    private void setBin(ListNode node) {
        final int i = getIndex(node.getVal());
        bin[i] = node;
    }

    private ListNode getBin(int t) {
        final int i = getIndex(t);
        return bin[i];
    }

    public void recursiveInsert(int t) {

    }


}
