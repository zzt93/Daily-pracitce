package competition.practice.qu2017;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;

/**
 * Created by zzt on 4/8/17.
 * <p>
 * <h3></h3>
 */
public class HappyCake {

    private static final int IMPOSSIBLE = -1;
    public static final char MINUS = '-';
    public static final char PLUS = '+';

    private enum Res {
        ALL_PLUS, ALL_MINUS, MIXED,
        PART_PLUS, PART_HAS_MINUS
    }

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/cake-large.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        in.nextLine();
        long res;
        for (int i = 0; i < trail; i++) {

            String[] split = in.nextLine().split(" ");
            res = cake(split[0].toCharArray(), split[0].length(), Integer.parseInt(split[1]));
            out.println("Case #" + (i + 1) + ": " + (res == IMPOSSIBLE ? "IMPOSSIBLE" : res));
            //            out.printf("Case #%d: %.7f\n", (i + 1), res);
        }
    }

    private static long cake(char[] chars, int e, int k) {
        if (e == 0) {
            return 0;
        }
        int c = 0;
        for (int i = e - 1; i >= 0; i--) {
            if (chars[i] == MINUS) {
                Res res = all(chars, i + 1 - k, i + 1);
                switch (res) {
                    case ALL_MINUS:
                        c++;
                        i -= k;
                        i++;
                        break;
                    case MIXED:
                        c++;
                        i = flip(chars, i + 1 - k, i + 1);
                        i++;
                        break;
                    case PART_HAS_MINUS:
                        return IMPOSSIBLE;
                    default:
                        assert false;
                }
            }
        }
        return c;
    }

    private static int flip(char[] chars, int s, int e) {
        int lastPlus = s;
        for (int i = s; i < e; i++) {
            if (chars[i] == PLUS) {
                lastPlus = i;
                chars[i] = MINUS;
            } else if (chars[i] == MINUS) {
                chars[i] = PLUS;
            }
        }
        return lastPlus;
    }

    private static Res all(char[] chars, int s, int e) {
        if (s < 0) {
            for (int i = 0; i < e; i++) {
                if (chars[i] == MINUS) {
                    return Res.PART_HAS_MINUS;
                }
            }
            return Res.PART_PLUS;
        } else {
            int c1 = 0, c2 = 0;
            for (int i = s; i < e; i++) {
                if (chars[i] == PLUS) {
                    c1++;
                } else if (chars[i] == MINUS) {
                    c2++;
                }
            }
            if (c1 == e - s) {
                return Res.ALL_PLUS;
            } else if (c2 == e - s) {
                return Res.ALL_MINUS;
            }
            return Res.MIXED;
        }
    }

}
