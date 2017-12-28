package pearls.searching;

import java.util.Random;
import java.util.function.Consumer;

/**
 * Created by zzt on 9/7/16.
 * <p>
 * number range [0, maxVal)
 * HashMap like container for Integer
 * <h3></h3>
 */
public class Bins {

    private static final int MAX_VAL = 100;
    private int maxVal;
    private ListNode[] bin;
    private ListNode sentinel;
    private static final int loadFactor = 8;
    private int size;

    public Bins(int maxVal) {
        int capacity = getIndex(maxVal) + 1;
        this.maxVal = maxVal;
        bin = new ListNode[capacity];
        sentinel = new ListNode(maxVal, null);
        initBin(bin);
    }

    private int getIndex(int val) {
        return val / loadFactor;
    }

    private void initBin(ListNode[] bin) {
        for (int i = 0; i < bin.length; i++) {
            bin[i] = sentinel;
        }
    }

    public void insert(int t) {
        size++;
        assert t < maxVal;
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
        size++;
        final ListNode bin = getBin(t);
        final ListNode node = SortedList1.insertAndReturnHead(bin, t);
        setBin(node);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder().append("Bins(").append(size).append(")" +
                ":\n");
        for (ListNode head : bin) {
            for (ListNode p = head; p != sentinel; p = p.getNext()) {
                builder.append(p.getVal()).append("->");
            }
            builder.append("\n");
        }
        builder.append("-------------------------\n");
        return builder.toString();
    }

    private static Random random = new Random(12);

    public static void main(String[] args) {
        testInsert(l -> {
            l.insert(random.nextInt(MAX_VAL));
        });
        testInsert(l -> {
            l.recursiveInsert(random.nextInt(MAX_VAL));
        });
    }

    private static void testInsert(Consumer<Bins> consumer) {
        final Bins bins = new Bins(MAX_VAL);
        for (int i = 0; i < 30; i++) {
            consumer.accept(bins);
            System.out.println(bins);
        }
    }
}
