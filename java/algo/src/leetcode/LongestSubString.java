package leetcode;

/**
 * Created by zzt on 3/20/16.
 * <p>
 * Essence:
 *     find a char ever exists in the already viewed string
 * </p>
 *
 * <p>
 * Solution:
 * <li>Trie tree</li>
 * <li>LinkedHashMap to find and count</li>
 */
public class LongestSubString {

    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        int res = 0;
        int i;
        for(i = 0; i < chars.length; i++) {
            if (res < i - start) {
                res = i - start;
            }
            char c = chars[i];
            String tmp = s.substring(start, i);
            int dupi = tmp.indexOf(c);
            if (dupi >= 0) {
                start += dupi + 1;
            }
        }
        if (res < i - start) {
            res = i - start;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new LongestSubString().lengthOfLongestSubstring("bbbbbb"));
    }
}
