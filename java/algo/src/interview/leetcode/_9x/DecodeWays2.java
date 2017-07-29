package interview.leetcode._9x;

/**
 * Created by zzt on 7/28/17.
 * <p>
 * <h3>DP, with more complex equation</h3>
 * <li>construct a two-dimension array:  a[An][An-1] = Sn</li>
 */
public class DecodeWays2 {

    private static final int M = 1000000000 + 7;

    public int numDecodings(String s) {
        if (s.isEmpty()) {
            return s.length();
        }
        char[] c = s.toCharArray();
        // invalid check
        if (c[0] == '0') return 0;

        long[] dp = new long[s.length() + 1];
        dp[0] = 1;
        dp[1] = c[0] == '*' ? 9 : 1;
        for (int i = 1; i < c.length; i++) {
            // invalid check
            if ((c[i - 1] >= '3' && c[i] == '0') || (c[i - 1] == '0' && c[i] == '0')) {
                return 0;
            }

            if (c[i] == '*') {
                if (c[i - 1] == '0' || c[i - 1] >= '3') {
                    dp[i + 1] = dp[i] % M * 9;
                } else if (c[i - 1] == '1') {
                    dp[i + 1] = dp[i - 1] % M * 9 + dp[i] % M * 9;
                } else if (c[i - 1] == '2') {
                    dp[i + 1] = dp[i - 1] % M * 6 + dp[i] % M * 9;
                } else if (c[i - 1] == '*') {
                    dp[i + 1] = dp[i - 1] % M * 9 +
                            dp[i - 1] % M * 6 +
                            dp[i] % M * 9;
                }
            } else if (c[i - 1] == '*') {
                if (c[i] >= '1' && c[i] <= '6') {
                    dp[i + 1] = dp[i] % M + dp[i - 1] % M * 2;
                } else if (c[i] == '0') {
                    dp[i + 1] = dp[i - 1] % M * 2;
                } else if (c[i] > '6') {
                    dp[i + 1] = dp[i] % M + dp[i - 1] % M;
                }
            } else {
                if (c[i] == '0') {
                    dp[i + 1] = dp[i - 1] % M;
                } else if (c[i - 1] >= '3' || (c[i - 1] == '2' && c[i] > '6') || c[i - 1] == '0') {
                    dp[i + 1] = dp[i] % M;
                } else {
                    dp[i + 1] = dp[i] % M + dp[i - 1] % M;
                }
            }
        }
        return (int) (dp[s.length()] % M);
    }

    public static void main(String[] args) {
        DecodeWays2 ways2 = new DecodeWays2();
        System.out.println(ways2.numDecodings("1*72*"));
        System.out.println(ways2.numDecodings("**4***"));
        System.out.println(ways2.numDecodings("**2***"));
        System.out.println(ways2.numDecodings("*0*"));
        System.out.println(ways2.numDecodings("*0"));
        System.out.println(ways2.numDecodings("*0**0"));
        System.out.println(ways2.numDecodings("**********"));
        System.out.println(ways2.numDecodings("***********"));
        System.out.println(ways2.numDecodings("********"));
        System.out.println(ways2.numDecodings("***"));
        System.out.println(ways2.numDecodings("**"));
        System.out.println(ways2.numDecodings("*"));
        System.out.println(ways2.numDecodings("1*"));
        System.out.println(ways2.numDecodings("*1*"));
        System.out.println(ways2.numDecodings("*1*7"));
        System.out.println(ways2.numDecodings("*17*"));
        System.out.println(ways2.numDecodings("*13*"));
        System.out.println(ways2.numDecodings("*10*"));
        System.out.println(ways2.numDecodings("*1*0"));
    }
}
