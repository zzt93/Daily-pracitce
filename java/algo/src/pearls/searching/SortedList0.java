package pearls.searching;

/**
 * Created by zzt on 11/23/16.
 * <p>
 * <h3></h3>
 */
public class SortedList0 implements SortedList{

    private ListNode head;
    private int size = 0;

    public SortedList0(int max) {
    }

    public void insert(int i) {
        size++;
        if (head == null) {
            head = new ListNode(i, null);
        } else {
            // find the last element smaller than i
            ListNode prev = null, next = head;
            while (next != null && next.getVal() < i) {
                prev = next;
                next = next.getNext();
            }
            if (prev == null) {
                head = new ListNode(i, head);
            } else {
                prev.setNext(new ListNode(i, next));
            }
        }
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder().append("SortedList0(").append(size).append("):");
        for (ListNode p = head; p != null; p = p.getNext()) {
            builder.append(p.getVal()).append("->");
        }
        return builder.toString();
    }
}
