package competition.practice.round1B2016;

import competition.utility.MyIn;
import competition.utility.MyOut;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by zzt on 5/7/16.
 * <p>
 * Usage:
 */
public class CloseMatch {

    private static final char MAX = '9';
    private static final char MIN = '0';

    private enum Compare {
        UNKNOWN,
        QUESTION_EQUAL,
        CODER,
        JAMMER,
    }

    private static final char QUES = '?';

    public static String[] closeMatch(String coder, String jammer) {
        final char[] c = coder.toCharArray();
        final char[] j = jammer.toCharArray();
        Compare res = Compare.UNKNOWN;
        int index;
        for (index = 0; index < c.length; index++) {
            if (c[index] != QUES && j[index] != QUES) {
                if (c[index] == j[index]) {
                    continue;
                }
                if (res == Compare.QUESTION_EQUAL) {
                    res = Compare.UNKNOWN;
                    break;
                } else { // no question mark before this difference
                    res = c[index] > j[index] ? Compare.CODER : Compare.JAMMER;
                    break;
                }
            } else { // has question mark
                res = Compare.QUESTION_EQUAL;
            }
        }
        if (res == Compare.UNKNOWN) {
            char[] large = c;
            char[] small = j;
            res = Compare.CODER;
            if (c[index] < j[index]) {
                large = c;
                small = j;
                res = Compare.JAMMER;
            }
            for (int i = 0; i < index; i++) {
                if (large[i] != QUES && small[i] != QUES
                        || large[i] == QUES && small[i] == '0'
                        || small[i] == QUES && large[i] == '9') {
                } else {
                    res = Compare.UNKNOWN;
                    break;
                }
            }
        }
        switch (res) {
            case CODER:
                setQuestion(c, MIN, j, MAX, 0);
                break;
            case JAMMER:
                setQuestion(c, MAX, j, MIN, 0);
                break;
            case QUESTION_EQUAL:
                setEqual(c, j, 0, j.length);
                break;
            case UNKNOWN: {
                assert index > 0;
                LinkedList<Integer> gaps = new LinkedList<>();
                gaps.add(0);
                gaps.add(-1);
                gaps.add(1);
                int mul = 1;
                for (int i = index; i < c.length; i++) {
                    if (c[i] == QUES || j[i] == QUES) {
                        continue;
                    }
                    final int mis = c[i] - j[i];
                    boolean positive = false;
                    for (ListIterator<Integer> it = gaps.listIterator(); it.hasNext(); ) {
                        int gap = it.next() * 10;
                        gap += mis;
                        positive = gap > 0;
                        if (Math.abs(gap) <= 5 * mul) {
                            it.set(gap);
                        } else {
                            it.remove();
                        }
                    }
                    assert gaps.size() >= 1;
//                    if (gaps.size() == 0) {
//                        if (positive) {
//                            setUnknown(c, j, index);
//                        } else {
//                            setUnknown(j, c, index);
//                        }
//                        return new String[]{
//                                new String(c),
//                                new String(j)
//                        };
//                    } else
                    if (gaps.size() == 1) {
                        if (gaps.getFirst() > 0) { // coder win
                            setUnknown(c, j, index);
                        } else {// jammer win
                            setUnknown(j, c, index);
                        }
                        return new String[]{
                                new String(c),
                                new String(j)
                        };
                    }
                    mul *= 10;
                }
                // gap is equal, choose a small one
                if (c[index] > j[index]) {
                    setUnknown(c, j, index);
                } else {
                    setUnknown(j, c, index);
                }
                break;
            }
        }

        return new String[]{
                new String(c),
                new String(j)
        };
    }

    private static boolean canGetNegative(char[] c, char[] j, int index) {
        return ((c[index - 1] != QUES) || (j[index - 1] != '0')) && (j[index - 1] != QUES || c[index - 1] != '9');
    }

    private static void setUnknown(char[] large, char[] small, int index) {
        // set [0, index - 1)
        setEqual(large, small, 0, index - 1);
        // set index - 1
        int addOn = large[index] < small[index] ? 1 : 0;
        if (large[index - 1] == QUES && small[index - 1] == QUES) {
            large[index - 1] = (char) (MIN + addOn);
            small[index - 1] = MIN;
        } else if (large[index - 1] == QUES) {
            large[index - 1] = (char) (small[index - 1] + addOn);
        } else if (small[index - 1] == QUES) {
            small[index - 1] = (char) (large[index - 1] - addOn);
        }
        for (int in = index + 1; in < large.length; in++) {
            setQuestion(large, MIN, small, MAX, index + 1);
        }
    }

    private static void setEqual(char[] c, char[] j, int start, int length) {
        for (int i = start; i < length; i++) {
            if (c[i] == QUES && j[i] == QUES) {
                c[i] = j[i] = MIN;
            } else if (c[i] == QUES) {
                c[i] = j[i];
            } else if (j[i] == QUES) {
                j[i] = c[i];
            }
        }
    }

    private static void setQuestion(char[] large, char s, char[] small, char l, int start) {
        for (int i = start; i < large.length; i++) {
            if (large[i] == QUES) {
                large[i] = s;
            }
            if (small[i] == QUES) {
                small[i] = l;
            }
        }
    }

    public static void main(String[] args) {
        MyIn in;
        MyOut out = new MyOut("res");
        try {
            in = new MyIn("testCase/close-match-small-practice1.in");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int trail = in.nextInt();
        in.nextLine();
        String[] res;
        for (int i = 0; i < trail; i++) {
            final String[] split = in.nextLine().split(" ");
            res = closeMatch(split[0], split[1]);
            out.println("Case #" + (i + 1) + ": " + res[0] + " " + res[1]);
        }
    }
}
