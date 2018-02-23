package interview.leetcode._3xx._31x;

/**
 * Created by zzt on 2/21/18.
 * <p>
 * <h3></h3>
 */
public class MaxProductOfWord {

    public int maxProduct(String[] words) {
        int[] ints = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            char[] cs = words[i].toCharArray();
            for (char c : cs) {
                ints[i] |= (1 << (c - 'a'));
            }
        }
        int max = 0;
        for (int i = 0; i < ints.length; i++) {
            for (int j = i + 1; j < ints.length; j++) {
                if ((ints[i] & ints[j]) == 0)
                    max = Math.max(max, words[i].length() * words[j].length());
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaxProductOfWord m = new MaxProductOfWord();
        System.out.println(m.maxProduct(new String[]{"abcw", "baz", "foo", "bar", "xtfn",
                "abcdef"}));
    }
}
