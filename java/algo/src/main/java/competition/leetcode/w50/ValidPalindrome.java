package competition.leetcode.w50;

/**
 * Created by zzt on 9/17/17.
 * <p>
 * <h3></h3>
 */
public class ValidPalindrome {

    public boolean validPalindrome(String s) {
        char[] cs = s.toCharArray();
        int l = cs.length;
        int i;
        for (i = 0; i < cs.length / 2; i++) {
            if (cs[i] != cs[l - 1 - i]) {
                break;
            }
        }
        if (i == l / 2) {
            return true;
        }
        // delete i
        int x;
        for (x = i + 1; x < (l + 1) / 2; x++) {
            if (cs[x] != cs[l - x]) {
                break;
            }
        }
        if (x == (l + 1) / 2) {
            return true;
        }
        // delete l-1-i
        for (int y = i; y < (l - 1) / 2; y++) {
            if (cs[y] != cs[l - 2 - y]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome validPalindrome = new ValidPalindrome();
        System.out.println(validPalindrome.validPalindrome(""));
        System.out.println(validPalindrome.validPalindrome("aabbcaa"));
        System.out.println(validPalindrome.validPalindrome("aabbaa"));
        System.out.println(validPalindrome.validPalindrome("aabcaa"));
        System.out.println(validPalindrome.validPalindrome("aabcaaa"));
    }
}
