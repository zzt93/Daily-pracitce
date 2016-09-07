package pearls.searching;

import java.util.List;

/**
 * Created by zzt on 9/7/16.
 * <p>
 * <h3></h3>
 */
public class SortedList {

    private static class Node {
        private int val;
        private Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    private int size;
    private Node head, sentinel;

    public SortedList(int max) {
        sentinel = head = new Node(max, null);
        size = 0;
    }

    public void report(List<Integer> list) {
        for (Node p = head; p != sentinel; p = p.next) {
            list.add(p.val);
        }
    }

    public void insert(int t) {

    }

    public void recursiveInsert(int t) {
        head = rInsert(head, t);
    }

    private Node rInsert(Node node, int t) {
        if (t < node.val) {
            return new Node(t, node);
        }
        return rInsert(node.next, t);
    }
}
