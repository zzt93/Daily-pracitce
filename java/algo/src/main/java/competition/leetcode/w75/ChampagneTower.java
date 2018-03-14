package competition.leetcode.w75;

import java.util.LinkedList;

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
    return 0;
  }

}
