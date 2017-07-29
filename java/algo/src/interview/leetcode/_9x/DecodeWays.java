package interview.leetcode._9x;

/**
 * Created by zzt on 7/27/17.
 * <p>
 * <h3>DP</h3>
 * <li>Sn = Sn-2, when an==0</li>
 * <li>Sn = Sn-1, when notChar(an-1, an)</li>
 * <li>Sn = Sn-2 + Sn-1, other</li>
 */
public class DecodeWays {

    public int numDecodings(String s) {
        if (s.isEmpty()) {
            return s.length();
        }
        char[] c = s.toCharArray();
        if (c[0] == '0') return 0;

        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 1; i < c.length; i++) {
            if ((c[i - 1] >= '3' && c[i] == '0') || (c[i - 1] == '0' && c[i] == '0')) {
                return 0;
            }
            if (c[i] == '0') {
                dp[i + 1] = dp[i - 1];
            } else if (c[i - 1] >= '3' || (c[i - 1] == '2' && c[i] > '6') || c[i - 1] == '0') {
                dp[i + 1] = dp[i];
            } else {
                dp[i + 1] = dp[i] + dp[i - 1];
            }
        }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        DecodeWays decodeWays = new DecodeWays();
        System.out.println(decodeWays.numDecodings(""));
        System.out.println(decodeWays.numDecodings("0"));
        System.out.println(decodeWays.numDecodings("1"));
        System.out.println(decodeWays.numDecodings("120"));
        System.out.println(decodeWays.numDecodings("123"));
        System.out.println(decodeWays.numDecodings("1234"));
        System.out.println(decodeWays.numDecodings("121212"));
        System.out.println(decodeWays.numDecodings("1200"));
        System.out.println(decodeWays.numDecodings("12030"));
        System.out.println(decodeWays.numDecodings("120310"));
    }
}
