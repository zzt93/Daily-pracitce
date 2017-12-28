package interview.leetcode._1xx._15x;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created by zzt on 11/7/17.
 * <p>
 * <h3></h3>
 */
public class EvaluateExpr {

    public int evalRPN(String[] tokens) {
        Map<String, BiFunction<Integer, Integer, Integer>> of = new HashMap<>();
        of.put("*", (f, s) -> f * s);
        of.put("+", (f, s) -> f + s);
        of.put("-", (f, s) -> f - s);
        of.put("/", (f, s) -> f / s);
        LinkedList<Integer> stack = new LinkedList<>();
        //        ImmutableMap<String, BiFunction<Integer, Integer, Integer>> of = ImmutableMap.of(
        //                "*", (f, s) -> f * s,
        //                "+", (f, s) -> f + s,
        //                "-", (f, s) -> f - s,
        //                "/", (f, s) -> f / s
        //        );
        for (String token : tokens) {
            if (of.containsKey(token)) {
                Integer s = stack.pop();
                Integer f = stack.pop();
                stack.push(of.get(token).apply(f, s));
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.getLast();
    }

    public static void main(String[] args) {
        EvaluateExpr evl = new EvaluateExpr();
        //        System.out.println(evl.evalRPN(new String[]{}));
        System.out.println(evl.evalRPN(new String[]{"18"}));
        System.out.println(evl.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        System.out.println(evl.evalRPN(new String[]{"4", "13", "5", "/", "+"}));
    }
}
