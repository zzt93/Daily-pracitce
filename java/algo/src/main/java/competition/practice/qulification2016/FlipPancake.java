package competition.practice.qulification2016;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;

/**
 * Created by zzt on 4/9/16.
 * <p>
 * Usage:
 */
public class FlipPancake {

    public static final char PLUS = '+';
    public static final char MINUS = '-';

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/flip-large.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        in.nextLine();
        int res;
        for (int i = 0; i < trail; i++) {
            String s = in.nextLine();
            StringBuilder sb = new StringBuilder(s);
            // TODO: 4/9/16 but why this is optimal?
            res = flip(sb, sb.length());
            out.println("Case #" + (i + 1) + ": " + res);
        }
    }

    private static int flip(StringBuilder sb, int end) {
        if (end == 0) {
            return 0;
        }
        char last = sb.charAt(end - 1);
        if (last == PLUS) {
            return flip(sb, end - 1);
        } else {
            // at least once for flipping last char
            int flipCount = 1;
            for (int i = 0; i < end; i++) {
                char c = sb.charAt(i);
                if (c == PLUS) {
                    sb.setCharAt(i, MINUS);
                    flipCount = 2;
                } else {
                    break;
                }
            }
            // flip all the string
            StringBuilder next = new StringBuilder(end - 1);
            for (int i = end - 1; i >= 0; i--) {
                next.append(sb.charAt(i) == PLUS ? MINUS : PLUS);
            }
            return flip(next, end - 1) + flipCount;
        }
    }
}
