package interview.leetcode._1xx._12x;

/**
 * Created by zzt on 9/27/17.
 * <p>
 * <h3></h3>
 */
public class ValidPalin {

    public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isAlphabetic(c)) {
                sb.append(Character.toLowerCase(c));
            } else if (Character.isDigit(c)) {
                sb.append(c);
            }
        }
        for (int i = 0; i < sb.length()/2; i++) {
            if (sb.charAt(i) != sb.charAt(sb.length()-1-i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalin validPalin = new ValidPalin();
        System.out.println(validPalin.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(validPalin.isPalindrome("race a car"));
        System.out.println(validPalin.isPalindrome("0p"));
    }
}
