package competition.leetcode.w64;

import java.util.LinkedList;

/**
 * Created by zzt on 12/25/17.
 * <p>
 * <h3></h3>
 */
public class MyStack {

    private LinkedList<Integer> queue = new LinkedList<>();
    /** Initialize your data structure here. */
    public MyStack() {
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
        for (int i = 0; i < queue.size()-1; i++) {
            queue.add(queue.removeFirst());
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.removeFirst();
    }

    /** Get the top element. */
    public int top() {
        return queue.peekFirst();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
