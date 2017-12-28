package pearls.searching;

/**
 * Created by zzt on 9/27/16.
 * <p>
 * <h3></h3>
 */
class ListNode {
    private int val;
    private ListNode next;

    public ListNode(int val, ListNode next) {
        this.setVal(val);
        this.setNext(next);
    }

    int getVal() {
        return val;
    }

    public ListNode getNext() {
        return next;
    }

    private void setVal(int val) {
        this.val = val;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
