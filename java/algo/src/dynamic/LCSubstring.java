package dynamic;

/**
 * Created by zzt on 4/4/16.
 * <p>
 * Usage:
 */
public class LCSubstring {

    public int LCSuffix(String s1, int end, String s2, int end2) {
        if (s1.charAt(end - 1) == s2.charAt(end2 - 1)) {
            return 1 + LCSuffix(s1, end - 1, s2, end2 - 1);
        }
        return 0;
    }

    public int LCSub(String s1, String s2) {
        int all = s1.length() + s2.length();
        for (int i = all; i > 2; i--) {
//            LCSuffix(s1, )
        }
        for (int i = s1.toCharArray().length - 1; i >= 0; i--) {
            for (int j = s2.toCharArray().length - 1; j >= 0; j--) {
                int len = LCSuffix(s1, s1.length(), s2, s2.length());
                if (i + 1 == len || j + 1 == len) {
                    return len;
                }
            }
        }
        return 0;
    }
}
