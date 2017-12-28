package interview.leetcode._2xx._23x;

import java.util.LinkedList;

/**
 * Created by zzt on 12/27/17.
 * <p>
 * <h3></h3>
 */
public class MyQueue {

    private LinkedList<Integer> from = new LinkedList<>();
    private LinkedList<Integer> to = new LinkedList<>();
    /** Initialize your data structure here. */
    public MyQueue() {
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        from.addLast(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (!to.isEmpty()) {
            return to.removeLast();
        }
        while (!from.isEmpty()) to.addLast(from.removeLast());

        return to.removeLast();
    }

    /** Get the front element. */
    public int peek() {
        if (!to.isEmpty()) {
            return to.peekLast();
        }
        while (!from.isEmpty()) to.addLast(from.removeLast());

        return to.peekLast();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return from.isEmpty() && to.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue q = new MyQueue();
        q.push(1);
        q.push(2);
        q.push(3);
        q.pop();
    }
}
