package interview.leetcode._13x;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by zzt on 10/26/17.
 * <p>
 * <h3>DP</h3>
 * <p>dimension: from left to right, one dimension</p>
 * <p>step: not one char, but one word</p>
 * <h3>Similar</h3>
 * <p>different coins to sum as a number</p>
 */
public class WordBreak {

    public boolean wordBreak(String str, List<String> wordDict) {
        HashSet<String> dict = new HashSet<>(wordDict);
        int l = str.length();
        char[] chars = str.toCharArray();
        boolean[] dp = new boolean[l + 1];
        dp[0] = true;

        for (int s = 0; s < l; s++) {
            for (int e = s + 1; e < l + 1; e++) {
                if (dp[s] && dict.contains(new String(chars, s, e - s))) {
                    dp[e] = true;
                }
            }
        }
        return dp[l];
    }

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        System.out.println(wordBreak.wordBreak("abc", Arrays.asList("ab", "a", "bc")));
        System.out.println(wordBreak.wordBreak("lele", Arrays.asList("ab", "a", "le")));
        System.out.println(wordBreak.wordBreak("leetcode", Arrays.asList("code", "a", "le")));
        System.out.println(wordBreak.wordBreak("leetcode", Arrays.asList("code", "leet", "let")));
    }
}
