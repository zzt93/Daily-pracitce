package competition.leetcode.w58;


import java.math.BigInteger;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by zzt on 11/12/17.
 * <p>
 * <h3></h3>
 */
public class NumOfAtoms {

    private static final int NORMAL = 0;
    private static final int DIGIT = 2;

    public String countOfAtoms(String formula) {
        char[] cs = formula.toCharArray();
        int l = cs.length;
        TreeMap<String, BigInteger> count = new TreeMap<>();
        recur(cs, 0, l, count);
        return count.entrySet().stream().map(e -> e.getKey() + (e.getValue().compareTo(BigInteger
                .ONE) > 0 ? e.getValue().toString() : "")).collect
                (Collectors.joining(""));
    }

    private int recur(char[] cs, int s, int e, TreeMap<String,
            BigInteger> count) {
        LinkedList<Character> atomStack = new LinkedList<>();
        TreeMap<String, BigInteger> tmp = new TreeMap<>();
        String now = null;
        int state = NORMAL;
        int i;
        for (i = s; i < e; i++) {
            char c = cs[i];
            if (c == '(') {
                addAtom(count, atomStack, tmp, now, state);
                i = recur(cs, i + 1, e, tmp);
                state = DIGIT;
                continue;
            } else if (c == ')') {
                break;
            }
            switch (state) {
                case NORMAL:
                    if (Character.isDigit(c)) {
                        now = popAll(atomStack);
                        state = DIGIT;
                        i--;
                    } else if (Character.isUpperCase(c)) {
                        now = popAll(atomStack);
                        if (!now.isEmpty()) {
                            count.put(now, count.getOrDefault(now, BigInteger.ZERO).add(BigInteger.ONE));
                        }
                        now = null;
                        atomStack.push(c);
                    } else { //lower case
                        atomStack.push(c);
                    }
                    break;
                case DIGIT:
                    if (!Character.isDigit(c)) {
                        i--;
                        addCount(count, atomStack, tmp, now);
                        state = NORMAL;
                    } else {
                        atomStack.push(c);
                    }
                    break;
                default:

            }
        }
        addAtom(count, atomStack, tmp, now, state);
        return i;
    }

    private void addAtom(TreeMap<String, BigInteger> count, LinkedList<Character> atomStack,
                         TreeMap<String, BigInteger> tmp, String now, int state) {
        switch (state) {
            case NORMAL:
                now = popAll(atomStack);
                if (!now.isEmpty()) {
                    count.put(now, count.getOrDefault(now, BigInteger.ZERO).add(BigInteger.ONE));
                }
                break;
            case DIGIT:
                addCount(count, atomStack, tmp, now);
                break;
        }
    }

    private void addCount(TreeMap<String, BigInteger> count, LinkedList<Character> atomStack,
                          TreeMap<String, BigInteger> tmp, String now) {
        String str = popAll(atomStack);
        if (str.isEmpty()) {
            str = "1";
        }
        BigInteger value = new BigInteger(str);
        if (tmp.isEmpty()) {
            if (now != null) {
                count.put(now, count.getOrDefault(now, BigInteger.ZERO).add(value));
            }
        } else {
            for (String s1 : tmp.keySet()) {
                count.put(s1, count.getOrDefault(s1, BigInteger.ZERO).add(tmp.get(s1).multiply
                        (value)));
            }
            tmp.clear();
        }
    }

    private String popAll(LinkedList<Character> stack) {
        StringBuilder s = new StringBuilder(stack.size());
        while (!stack.isEmpty()) {
            s.insert(0, stack.poll());
        }
        return s.toString();
    }

    public static void main(String[] args) {
        NumOfAtoms n = new NumOfAtoms();
        System.out.println(n.countOfAtoms("((N7Li31C7B10Be37B23H2H11Li40Be15)26(OBLi48B46N4)25(O48C22He)2N10O34N15B33Li39H34H26B15B23C31(C36N38O33Li38H15H46He21Be38B50)8)3"));
        System.out.println(n.countOfAtoms("((HHe28Be26He)9)34"));
        System.out.println(n.countOfAtoms("()"));
        System.out.println(n.countOfAtoms("H2O"));
        System.out.println(n.countOfAtoms("Mg(OH)2"));
        System.out.println(n.countOfAtoms("(ABCD)(EF)"));
        System.out.println(n.countOfAtoms("(ABCD)2(EF)3"));
        System.out.println(n.countOfAtoms("AF(ABCD)2(EF)3"));
        System.out.println(n.countOfAtoms("K4(ON(SO3)2)2"));
        System.out.println(n.countOfAtoms("AF(ABCD)2(EF(He2A)2)3"));
    }
}
