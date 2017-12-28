package competition.leetcode.w40;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zzt on 7/9/17.
 * <p>
 * <h3></h3>
 */
public class SolveEquation {

    private Pattern num = Pattern.compile("([+|-]?[0-9]+(?!x))");
    private Pattern x = Pattern.compile("([+|-]?[0-9]*x)");

    public String solveEquation(String equation) {
        String[] split = equation.split("=");
        String left = split[0];
        String right = split[1];

        int numS = extractNum(left) - extractNum(right);
        int xCo = -extractCo(left) + extractCo(right);
        if (xCo == 0) {
            if (numS == 0) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        }
        return "x=" + (numS / xCo);
    }

    private int extractCo(String s) {
        int res = 0;
        Matcher matcher = x.matcher(s);
        while (matcher.find()) {
            String group = matcher.group();
            String substring = group.substring(0, group.length() - 1);
            if (substring.equals("-")) {
                res--;
                continue;
            } else if (substring.isEmpty() || substring.equals("+")) {
                res++;
                continue;
            }
            res += Integer.parseInt(substring);
        }
        return res;
    }

    private int extractNum(String s) {
        int res = 0;
        StringBuilder sb = new StringBuilder(s);
        Matcher xm = x.matcher(s);
        while (xm.find()) {
            int capacity = xm.end() - xm.start();
            StringBuilder tmp = new StringBuilder(capacity);
            for (int i = 0; i < capacity; i++) {
                tmp.append(" ");
            }
            sb.replace(xm.start(), xm.end(), tmp.toString());
        }
        Matcher matcher = num.matcher(sb);
        while (matcher.find()) {
            String n = matcher.group();
            res += Integer.parseInt(n);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new SolveEquation().solveEquation("2x+3x-6x=30x"));
        System.out.println(new SolveEquation().solveEquation("2x+3x-6x=x+2"));
        System.out.println(new SolveEquation().solveEquation("x+5-3+x=6+x-2"));
        System.out.println(new SolveEquation().solveEquation("x=x+2"));
        System.out.println(new SolveEquation().solveEquation("x=2x+20"));
    }
}
