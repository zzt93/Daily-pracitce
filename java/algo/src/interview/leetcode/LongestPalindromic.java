package interview.leetcode;

import competition.utility.RandomStr;

/**
 * Created by zzt on 7/6/16.
 * <p>
 * <h3></h3>
 * @see WrongLongestPalindromic
 * @see WrongLongestSub
 */
public class LongestPalindromic {

    public static String lpDynamic(String string) {
        if (string.isEmpty()) {
            return "";
        }
        int[] palinEndHere = new int[string.length()];
        palinEndHere[0] = 1;
        int maxLen = 1, maxEndI = 0;
        final char[] chars = string.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            int symI = i - palinEndHere[i - 1];
            if (symI >= 0 && c == chars[symI]) {
                palinEndHere[i] = palinEndHere[i - 1] + 1;
            } else {
                palinEndHere[i] = 1;
            }
            if (palinEndHere[i] > maxLen) {
                maxLen = palinEndHere[i];
                maxEndI = i;
            }
        }
        return string.substring(maxEndI - maxLen + 1, maxEndI + 1);
    }

    public static void main(String[] args) {
        final WrongLongestSub wrongLongestSub = new WrongLongestSub();
        output(wrongLongestSub, "abcdba");
        for (int i = 0; i < 10; i++) {
            final String s = RandomStr.sameSeed(10);
            output(wrongLongestSub, s);
        }
    }

    private static void output(WrongLongestSub wrongLongestSub, String s) {
        System.out.print(s + ": ");
        System.out.print(lpDynamic(s) + ": ");
        System.out.println(wrongLongestSub.longestPalindrome(s));
    }
}
