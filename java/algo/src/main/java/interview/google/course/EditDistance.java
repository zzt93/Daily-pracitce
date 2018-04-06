package interview.google.course;

import java.util.Scanner;

/**
 * Created by zzt on 3/19/18.
 * <p>
 * <h3></h3>
 */
public class EditDistance {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for (int i = 0; i < t; i++) {
            s.nextLine();s.nextLine();
            System.out.println(edit(s.next(), s.next()));
        }
    }

    private static int edit(String f, String s) {
        int fl = f.length(), sl = s.length();
        char[] fc = f.toCharArray(), sc = s.toCharArray();
        int[][] dp = new int[fl][sl];
        dp[0][0] = (fc[0] == sc[0] ? 0 : 1);
        for (int i = 1; i < fl; i++) {
            dp[i][0] = (fc[i] == sc[0] ? i : dp[i - 1][0] + 1);
        }
        for (int i = 1; i < sl; i++) {
            dp[0][i] = (fc[0] == sc[i] ? i : dp[0][i - 1] + 1);
        }
        for (int i = 1; i < fl; i++) {
            for (int x = 1; x < sl; x++) {
                if (fc[i] == sc[x]) {
                    dp[i][x] = dp[i - 1][x - 1];
                } else {
                    dp[i][x] = Math.min(dp[i-1][x-1], Math.min(dp[i - 1][x], dp[i][x - 1])) + 1;
                }
            }
        }
        return dp[fl - 1][sl - 1];
    }
}
