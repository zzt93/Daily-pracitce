package competition.leetcode.w43;

import java.util.Arrays;

/**
 * Created by zzt on 7/30/17.
 * <p>
 * <h3></h3>
 */
public class TwoKeyKeyboard {

    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
        for (int i = 1; i < dp.length; i++) {
            int time = 0;
            for (int x = i + i; x < dp.length; x += i) {
                time++;
                dp[x] = Math.min(dp[x], dp[i] + time + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        TwoKeyKeyboard twoKeyKeyboard = new TwoKeyKeyboard();
        System.out.println(twoKeyKeyboard.minSteps(10));
        System.out.println(twoKeyKeyboard.minSteps(25));
        System.out.println(twoKeyKeyboard.minSteps(100));
        System.out.println(twoKeyKeyboard.minSteps(5));
        System.out.println(twoKeyKeyboard.minSteps(17));
        System.out.println(twoKeyKeyboard.minSteps(9));
        System.out.println(twoKeyKeyboard.minSteps(999));
        System.out.println(twoKeyKeyboard.minSteps(1000));
        System.out.println(twoKeyKeyboard.minSteps(3));
        System.out.println(twoKeyKeyboard.minSteps(8));
    }
}
