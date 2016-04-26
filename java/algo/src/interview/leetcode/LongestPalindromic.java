package interview.leetcode;

/**
 * Created by zzt on 4/4/16.
 * <p>
 * Problem:
 * In essence, this problem is to find some substring in a long string
 * <li>brute force: compare and remember</li>
 * <ul>
 * <li>starting from the middle and compare adjacent value</li>
 * </ul>
 * <li>treat it as a longest-common subString problem</li>
 * <ul>
 * <li>dynamic way: find a prefix from end</li>
 * <li>plain way: String f, s; find s[0] in f, then try find s[1] in adjacent position;
 * if fail, find s[1] in f, find s[2] in adjacent position ...
 * </li>
 * <li>suffix tree</li>
 * </ul>
 * <li>suffix tree(which can find substring fast): reverse and compare</li>
 */
public class LongestPalindromic {
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

    private Res lcs(String f, int fe, String s, int se) {
        if (fe == 0 || se == 0) {
            return new Res();
        }
        char fLast = f.charAt(fe - 1);
        char sLast = s.charAt(se - 1);
        if (fLast == sLast) {
            return lcs(f, fe - 1, s, se - 1).append(fLast);
        } else {
            return new Res();
        }
    }

    private String LCS(String f, String s) {
        int fSize = f.length();
        int sSize = s.length();
        Res max = new Res();
        for (int i = fSize; i > 0; i--) {
            for (int j = sSize; j > 0; j--) {
                Res tmp = lcs(f, i, s, j);
                if (max.count < tmp.count) {
                    max = tmp;
                }
            }
        }
        return max.getString();
    }

    public String longestPalindrome(String s) {
        StringBuilder sb = new StringBuilder(s);
        return LCS(s, sb.reverse().toString());
    }
}
