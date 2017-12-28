package competition.leetcode.week23;

/**
 * Created by zzt on 3/12/17.
 * <p>
 * <h3></h3>
 */
public class ReverseString2 {

    public String reverseStr(String s, int k) {
        int len = s.length();
        final char[] chars = s.toCharArray();
        for (int i = 0; i < len; i += (2 * k)) {
            final int endI = Math.min(i + k, len);
            reverse(chars, i, endI);
        }
        return new String(chars);
    }

    private void reverse(char[] chars, int sI, int endI) {
        int mid = (sI + endI) / 2;
        int pivot = (sI + endI - 1);
        for (int i = sI; i < mid; i++) {
            char t = chars[i];
            final int sym = pivot - i;
            chars[i] = chars[sym];
            chars[sym] = t;
        }
    }

    public static void main(String[] args) {
        final ReverseString2 reverseString2 = new ReverseString2();
        System.out.println(reverseString2.reverseStr("abcdefghi", 3));
        System.out.println(reverseString2.reverseStr("abcdefg", 2));
        System.out.println(reverseString2.reverseStr("abcdefg", 4));
        System.out.println(reverseString2.reverseStr("ab", 2));
    }
}
