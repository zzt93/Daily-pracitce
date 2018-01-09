package interview.leetcode._2xx._24x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 1/4/18.
 * <p>
 * <h3></h3>
 */
public class DifferentParen {

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        char[] cs = input.toCharArray();
        int len = cs.length;
        boolean op = false;
        for (int i = 0; i < len; i++) {
            char c = cs[i];
            if (c == '+' || c == '-' || c == '*') {
                op = true;
                List<Integer> l1 = diffWaysToCompute(new String(cs, 0, i));
                List<Integer> l2 = diffWaysToCompute(new String(cs, i + 1, len-i-1));
                for (Integer x : l1) {
                    for (Integer y : l2) {
                        if (c == '+') res.add(x + y);
                        else if (c == '-') res.add(x - y);
                        else res.add(x * y);
                    }
                }
            }
        }
        if (!op) {
            res.add(Integer.parseInt(input));
        }
        return res;
    }

    public static void main(String[] args) {
        DifferentParen p = new DifferentParen();
        System.out.println(p.diffWaysToCompute("1"));
        System.out.println(p.diffWaysToCompute("1+2"));
        System.out.println(p.diffWaysToCompute("1+2-3"));
        System.out.println(p.diffWaysToCompute("1+2-3*4"));
        System.out.println(p.diffWaysToCompute("1+2-3*4+5"));
        System.out.println(p.diffWaysToCompute("1+2-3*4+5-6"));
    }
}
