package interview.leetcode._2xx._22x;

import java.util.LinkedList;

/**
 * Created by zzt on 12/23/17.
 * <p>
 * <h3></h3>
 */
public class Calculator {

    private static final int IN_TOKEN = 0;
    private static final int FIND_TOKEN = 1;
    private static final int FIND_OP = 2;

    public int calculate(String s) {
        LinkedList<Integer> operand = new LinkedList<>();
        LinkedList<Character> operator = new LinkedList<>();
        char[] cs = s.toCharArray();
        StringBuilder token = new StringBuilder();
        int state = FIND_TOKEN;
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            switch (state) {
                case IN_TOKEN:
                    if (c == ' ' || c == '+' || c == '-' || c == ')') {
                        operand.add(Integer.parseInt(token.toString()));
                        token.setLength(0);
                        state = FIND_OP;
                        if (c == '+' || c == '-') i--;
                        else if (c == ')') {
                            cal(operand, operator);
                            assert operator.peekLast() == '(';
                            operator.removeLast();
                        }
                    } else token.append(c);
                    break;
                case FIND_TOKEN:
                    if (c == ' ') continue;
                    else if (c == '(') {
                        operator.add('(');
                    } else if (c == '+' || c == '-') {
                        // 1 + + 1
                        token.append(c);
                    } else {
                        state = IN_TOKEN;
                        token.append(c);
                    }
                    break;
                case FIND_OP:
                    if (c == '+' || c == '-') {
                        if (!operator.isEmpty() && operator.peekLast() != '(') {
                            cal(operand, operator);
                        }
                        operator.add(c);
                        state = FIND_TOKEN;
                    }
                    break;
            }
        }
        if (token.length() != 0) {
            operand.add(Integer.parseInt(token.toString()));
        }
        cal(operand, operator);
        return operand.removeLast();
    }

    private void cal(LinkedList<Integer> operand, LinkedList<Character> operator) {
        char op = operator.removeLast();
        int s = operand.removeLast();
        int f = operand.removeLast();
        operand.add(op == '+' ? f + s : f - s);
    }

    public static void main(String[] args) {
        Calculator c = new Calculator();
        System.out.println(c.calculate("1 ++1"));
        System.out.println(c.calculate("1 +-1"));
        System.out.println(c.calculate("1 + -1"));
        System.out.println(c.calculate("1 + - 1"));
        System.out.println(c.calculate("1 + (- 1 + 1)"));
        System.out.println(c.calculate(" 2-1 + 2 "));
        System.out.println(c.calculate("(1+(4+5+2)-3)+(6+8)"));
    }
}
