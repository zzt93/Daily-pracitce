package pearls.searching;

import java.util.Random;
import java.util.function.Consumer;

/**
 * Created by zzt on 9/27/16.
 * <p>
 * <h3></h3>
 */
public class SortedList2 implements SortedList {

    private static Random random = new Random(12);
    private int size;
    private ListNode head, sentinel;

    public SortedList2(int max) {
        sentinel = new ListNode(max, null);
        head = new ListNode(max, sentinel);
        size = 0;
    }

    public void insert(int t) {
        ListNode prev = head, next = prev.getNext();
        while (next.getVal() < t) {
            prev = next;
            next = next.getNext();
        }
        prev.setNext(new ListNode(t, next));
        size++;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder().append("SortedList2(").append(size)
                .append("):");
        for (ListNode p = head; p != sentinel; p = p.getNext()) {
            builder.append(p.getVal()).append("->");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        testLoop(l -> {
            l.insert(random.nextInt(100));
        });
    }

    private static void testLoop(Consumer<SortedList2> consumer) {
        final SortedList2 list = new SortedList2(Integer.MAX_VALUE);
        for (int i = 0; i < 10; i++) {
            consumer.accept(list);
            System.out.println(list);
        }
    }
}
