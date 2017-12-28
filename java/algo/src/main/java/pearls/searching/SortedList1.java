package pearls.searching;


import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

/**
 * Created by zzt on 9/7/16.
 * <p>
 * <h3></h3>
 */
public class SortedList1 implements SortedList {

    private static Random random = new Random(12);

    private int size;
    private ListNode head, sentinel;

    public SortedList1(int max) {
        sentinel = head = new ListNode(max, null);
        size = 0;
    }

    public void report(List<Integer> list) {
        for (ListNode p = head; p != sentinel; p = p.getNext()) {
            list.add(p.getVal());
        }
    }

    @Override
    public void insert(int t) {
        ListNode succ = head, prev = null;
        while (succ.getVal() < t) {
            prev = succ;
            succ = succ.getNext();
        }
        final ListNode listNode = new ListNode(t, succ);
        if (prev == null) {
            head = listNode;
        } else {
            prev.setNext(listNode);
            assert t <= succ.getVal() && t > prev.getVal();
        }
        size++;
    }

    public void recursiveInsert(int t) {
        size++;
        head = insertAndReturnHead(head, t);
    }

    static ListNode insertAndReturnHead(ListNode listNode, int t) {
        if (t <= listNode.getVal()) {
            return new ListNode(t, listNode);
        }
        listNode.setNext(insertAndReturnHead(listNode.getNext(), t));
        return listNode;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder().append("SortedList1(").append(size)
                .append("):");
        for (ListNode p = head; p != sentinel; p = p.getNext()) {
            builder.append(p.getVal()).append("->");
        }
        return builder.toString();
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        testInsert(l -> {
            l.insert(random.nextInt(100));
        });
        //        testInsert(l -> {
        //            l.recursiveInsert(random.nextInt(100));
        //        });
    }

    private static void testInsert(Consumer<SortedList> consumer) {
        final SortedList0 list0 = new SortedList0(Integer.MAX_VALUE);
        final SortedList1 list1 = new SortedList1(Integer.MAX_VALUE);
        final SortedList2 list2 = new SortedList2(Integer.MAX_VALUE);
        for (int i = 0; i < 100; i++) {
            consumer.accept(list0);
            System.out.println(list0);
            consumer.accept(list1);
            System.out.println(list1);
            consumer.accept(list2);
            System.out.println(list2);
        }
    }
}
