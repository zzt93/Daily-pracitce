package interview.leetcode._2xx._22x;

import java.util.LinkedList;

/**
 * Created by zzt on 12/25/17.
 * <p>
 * <h3></h3>
 */
public class Calculator2 {

    private static final int FIND_NUM = 0;
    private static final int IN_NUM = 1;
    private static final int FIND_OP = 2;

    public int calculate(String s) {
        char[] cs = s.toCharArray();
        LinkedList<Character> operator = new LinkedList<>();
        LinkedList<Integer> operand = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int state = FIND_NUM;
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            switch (state) {
                case FIND_NUM:
                    if (c == ' ') continue;
                    else {
                        sb.append(c);
                        state = IN_NUM;
                    }
                    break;
                case IN_NUM:
                    if (c == ' ' || isOp(c)) {
                        operand.add(Integer.parseInt(sb.toString()));
                        sb.setLength(0);
                        state = FIND_OP;
                        i--;
                    } else sb.append(c);
                    break;
                case FIND_OP:
                    if (isOp(c)) {
                        if (level1(c) || (level2(c) && !operator.isEmpty() && level2(operator
                                .peekLast()))) {
                            cal(operator, operand);
                            if (level1(c) && !operator.isEmpty()) cal(operator, operand);
                        }
                        operator.add(c);
                        state = FIND_NUM;
                    }
                    break;
            }
        }
        if (sb.length() != 0) operand.add(Integer.parseInt(sb.toString()));
        while (operand.size() > 1) cal(operator, operand);
        assert operand.size() == 1;
        return operand.removeLast();
    }

    private void cal(LinkedList<Character> operator, LinkedList<Integer> operand) {
        if (operand.size() < 2 || operator.size() < 1) {
            return;
        }
        Integer s = operand.removeLast();
        Integer f = operand.removeLast();
        switch (operator.removeLast()) {
            case '*':
                operand.add(f * s);
                break;
            case '/':
                operand.add(f / s);
                break;
            case '+':
                operand.add(f + s);
                break;
            case '-':
                operand.add(f - s);
                break;
        }
    }

    private boolean isOp(char c) {
        return level1(c) || level2(c);
    }

    private boolean level2(char c) {
        return c == '*' || c == '/';
    }

    private boolean level1(char c) {
        return c == '+' || c == '-';
    }

    public static void main(String[] args) {
        Calculator2 c = new Calculator2();
        System.out.println(c.calculate("1*2-3/4+5*6-7*8+9/10"));
        System.out.println(c.calculate("1*2*2-3-3/4+5*6-7*8+9/10"));
        System.out.println(c.calculate("3"));
        System.out.println(c.calculate("3++1"));
        System.out.println(c.calculate("3+2*7"));
        System.out.println(c.calculate(" 3/2 "));
        System.out.println(c.calculate("3+5 / 2"));
    }
}
