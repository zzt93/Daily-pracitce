package competition.leetcode.w42;

import java.util.HashMap;

/**
 * Created by zzt on 7/23/17.
 * <p>
 * <h3></h3>
 */
public class PalindromicStr {

    private static class Sub {
        private int s;
        private int e;

        Sub(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public boolean equals(Object o) {
            Sub sub = (Sub) o;
            return s == sub.s && e == sub.e;
        }

        @Override
        public int hashCode() {
            int result = s;
            result = 31 * result + e;
            return result;
        }
    }

    public int countSubstringsTLE(String s) {
        // substring to boolean
        HashMap<Sub, Boolean> dp = new HashMap<>();
        char[] c = s.toCharArray();
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            // pre: [i, i+1) is palindromic
            dp.put(new Sub(i, i + 1), Boolean.TRUE);
            res++;
            // check [x, i+1) is palindromic
            for (int x = i - 1; x >= 0; x--) {
                // check sub-problem [x+1, i)
                boolean orDefault = dp.getOrDefault(new Sub(x + 1, i), Boolean.FALSE);
                if (c[i] == c[x] && (orDefault || x == i - 1)) {
                    dp.put(new Sub(x, i + 1), Boolean.TRUE);
                    res++;
                }
            }
        }
        return res;
    }

    private int countSubstrings(String s) {
        int[][] dp = new int[1001][1001];
        char[] c = s.toCharArray();
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            // pre: [i, i+1) is palindromic
            dp[i][i + 1] = 1;
            res++;
            // check [x, i+1) is palindromic
            for (int x = i - 1; x >= 0; x--) {
                // check sub-problem [x+1, i)
                boolean palin = (dp[x + 1][i] == 1);
                if (c[i] == c[x] && (palin || x == i - 1)) {
                    dp[x][i + 1] = 1;
                    res++;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        PalindromicStr palindromicStr = new PalindromicStr();
        palindromicStr.countSubstrings("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(palindromicStr.countSubstrings("aaa"));
        System.out.println(palindromicStr.countSubstrings("aaaa"));
        System.out.println(palindromicStr.countSubstrings("abcd"));
    }

}
