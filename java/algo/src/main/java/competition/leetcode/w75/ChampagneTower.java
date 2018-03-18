package competition.leetcode.w75;

/**
 * @author zzt
 */
public class ChampagneTower {

  public static void main(String[] args) {
    ChampagneTower c = new ChampagneTower();
    System.out.println(c.champagneTower(6, 2, 1)); // 1
    System.out.println(c.champagneTower(6, 3, 1)); // 0.25
    System.out.println(c.champagneTower(7, 3, 1)); // 0.5
    System.out.println(c.champagneTower(9, 3, 0)); // 0.25
    System.out.println(c.champagneTower(4, 2, 1)); // 0.5
    System.out.println(c.champagneTower(4, 2, 0)); // 0.25
    System.out.println(c.champagneTower(0, 1, 0)); // 0


    System.out.println(c.champagneTower(2, 0, 0));
    System.out.println(c.champagneTower(1, 1, 0));
    System.out.println(c.champagneTower(2, 1, 0));
    System.out.println(c.champagneTower(2, 1, 1));
    System.out.println(c.champagneTower(4, 2, 1));
    System.out.println(c.champagneTower(4, 2, 2));
    System.out.println(c.champagneTower(12, 2, 2));
    System.out.println(c.champagneTower(2, 2, 2));
    System.out.println(c.champagneTower(12, 4, 0));
    System.out.println(c.champagneTower(12, 4, 1));
    System.out.println(c.champagneTower(12, 4, 2));
  }

  public double champagneTower(int poured, int query_row, int query_glass) {
    double[][] dp = new double[101][101];
    dp[0][0] = poured;
    for (int i = 0; i <= query_row; i++) {
      for (int j = 0; j < i+1; j++) {
        if (dp[i][j] > 1) {
          dp[i+1][j] += (dp[i][j]-1)/2;
          dp[i+1][j+1] += (dp[i][j]-1)/2;
          dp[i][j] = 1;
        }
      }
    }
    return dp[query_row][query_glass];
  }

}
