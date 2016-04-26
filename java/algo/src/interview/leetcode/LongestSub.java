package interview.leetcode;

/**
 * Created by zzt on 4/11/16.
 * <p>
 * Usage:
 */
public class LongestSub {

    class Res {
        int count;
        StringBuilder sb = new StringBuilder();

        public Res append(char c) {
            count++;
            sb.append(c);
            return this;
        }

        public String getString() {
            return sb.toString();
        }
    }

    private Res lcs(String f, int fs, String s) {
        char ch = f.charAt(fs);
        int first = s.indexOf(ch);
        Res tmp = new Res();
        if (first < 0) {
            return tmp;
        }
        Res max = new Res();
        for (int i = first; i < s.length() && fs < f.length(); i++) {
            char sc = s.charAt(i);
            char fc = f.charAt(fs++);
            if (sc == fc) {
                tmp.append(sc);
            } else {
                i = s.indexOf(ch, first + 1);
                if (max.count < tmp.count) {
                    max = tmp;
                }
                tmp = new Res();
                if (i < 0) {
                    return max;
                }
            }
        }
        if (max.count < tmp.count) {
            max = tmp;
        }
        return max;
    }

    private String LCS(String f, String s) {
        int fSize = f.length();
        Res max = new Res();
        for (int i = 0; i < fSize; i++) {
            Res tmp = lcs(f, i, s);
            if (max.count < tmp.count) {
                max = tmp;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            Res tmp = lcs(s, i, f);
            if (max.count < tmp.count) {
                max = tmp;
            }
        }
        return max.getString();
    }

    public String longestPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        return LCS(s, sb.reverse().toString());
    }

    public static void main(String[] args) {
        System.out.println(new LongestSub().longestPalindrome("abb"));
    }
}
