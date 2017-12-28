package competition.leetcode.w51;

import java.util.LinkedList;

/**
 * Created by zzt on 9/24/17.
 * <p>
 * <h3></h3>
 */
public class Baseball {

    public int calPoints(String[] ops) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < ops.length; i++) {
            String op = ops[i];
            switch (op) {
                case "C":
                    stack.pop();
                    break;
                case "D":
                    stack.push(stack.peek() * 2);
                    break;
                case "+":
                    Integer f = stack.pop();
                    Integer s = stack.pop();
                    stack.push(s);
                    stack.push(f);
                    stack.push(f + s);
                    break;
                default:
                    stack.push(Integer.parseInt(op));
                    break;
            }
        }
        int sum = 0;
        for (Integer integer : stack) {
            sum += integer;
        }
        return sum;
    }

    public static void main(String[] args) {
        Baseball baseball = new Baseball();
        System.out.println(baseball.calPoints(new String[]{"5","-2","4","C","D","9","+","+"}));
        System.out.println(baseball.calPoints(new String[]{"5", "2", "C", "D", "+"}));
    }
}
