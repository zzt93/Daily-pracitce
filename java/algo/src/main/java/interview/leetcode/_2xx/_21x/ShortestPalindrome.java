package interview.leetcode._2xx._21x;

import java.io.IOException;
import java.util.LinkedList;

/**
 * Created by zzt on 12/12/17.
 * <p>
 * <h3>Palindrome</h3>
 * <ul>
 * <li>DP: cs[s] == cs[e] && same(s+1,e-1)</li>
 * <li>reverse and compare</li>
 * <li>KMP: compare two string, skip old compare; compare prefix and suffix</li>
 * </ul>
 *
 * @see interview.leetcode._0xx.before.LongestPalindromic
 */
public class ShortestPalindrome {

    public String shortestPalindromeTLE(String s) {
        char[] cs = s.toCharArray();
        int l = cs.length;
        if (l <= 1) {
            return s;
        }
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 1; i < l; i++) {
            char c = cs[i];
            if (c == cs[0]) {
                queue.addFirst(i);
            }
        }

        int find = -1;
        for (Integer index : queue) {
            if (recur(cs, 0, index + 1)) {
                find = index;
                break;
            }
        }
        StringBuilder sb = new StringBuilder(s);
        if (find != -1) {
            return sb.insert(0, new StringBuilder(s.substring(find + 1)).reverse()).toString();
        } else {
            return sb.reverse().append(s.substring(1)).toString();
        }
    }

    private boolean recur(char[] cs, int s, int e) {
        assert cs[s] == cs[e - 1];
        while (s < e && cs[s + 1] == cs[e - 2]) {
            s++;
            e--;
        }
        return s >= e;
    }

    public static void main(String[] args) throws IOException {
        ShortestPalindrome s = new ShortestPalindrome();
        System.out.println(s.shortestPalindrome("a"));
        System.out.println(s.shortestPalindrome("ac"));
        System.out.println(s.shortestPalindrome("aa"));
        System.out.println(s.shortestPalindrome("aac.e.caaa"));
        System.out.println(s.shortestPalindrome("aac"));
        System.out.println(s.shortestPalindrome("caac"));
        System.out.println(s.shortestPalindrome("abcd"));
    }

    public String shortestPalindrome(String s) {
        String temp = s + "#" + new StringBuilder(s).reverse().toString();
        int[] table = kmp(temp);
        int l = table.length;

        return new StringBuilder(s.substring(table[l-1])).reverse().append(s).toString();
    }

    public static int[] kmp(String temp) {
        int[] res = new int[temp.length()];
        char[] cs = temp.toCharArray();
        res[0] = 0;
        int lastMaxPrefix = 0;
        for (int i = 1; i < cs.length; i++) {
            assert lastMaxPrefix == res[i - 1];
            if (cs[i] == cs[lastMaxPrefix]) {
                res[i] = res[i - 1] + 1;
                lastMaxPrefix++;
            } else {
                while (lastMaxPrefix > 0 && cs[i] != cs[lastMaxPrefix]) {
                    lastMaxPrefix = res[lastMaxPrefix - 1];
                }
                if (cs[i] == cs[lastMaxPrefix]) {
                    lastMaxPrefix++;
                }
                res[i] = lastMaxPrefix;
            }
        }
        return res;
    }
}
