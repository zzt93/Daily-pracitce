package interview.leetcode._1xx._15x;

import java.util.LinkedList;

/**
 * Created by zzt on 11/11/17.
 * <p>
 * <h3></h3>
 */
public class MinStack {

    private LinkedList<Integer> stack = new LinkedList<>();
    private int min = Integer.MAX_VALUE;

    public MinStack() {
    }

    public void push(int x) {
        if (x < min) {
            min = x;
        }
        stack.push(min);
        stack.push(x);
    }

    public void pop() {
        stack.pop();
        stack.pop();
        if (!stack.isEmpty()) {
            Integer pop = stack.pop();
            min = stack.peek();
            stack.push(pop);
        } else {
            min = Integer.MAX_VALUE;
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack m = new MinStack();
        m.push(-2);
        m.push(0);
        m.push(-3);
        System.out.println(m.getMin());
        m.pop();
        System.out.println(m.top());
        System.out.println(m.getMin());
    }
}
