package competition.leetcode.w75;

import java.util.LinkedList;

/**
 * @author zzt
 */
public class ChampagneTower {

  public static void main(String[] args) {
    ChampagneTower c = new ChampagneTower();
    System.out.println(c.champagneTower(6, 2, 1));
    System.out.println(c.champagneTower(0, 1, 0));
    System.out.println(c.champagneTower(1, 0, 0));
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
    int now = 0;
    for (int r = 0; r < query_row; r++) {
      now += (r + 1);
    }
    int left = poured - now;
    if (left < 0) return 0;
    LinkedList<Integer> line = new LinkedList<>();
    line.push(1);
    long all = 1;
    for (int i = 1; i <= query_row; i++) {
      int f = line.peekFirst();
      long sum = 0;
      LinkedList<Integer> tmp = new LinkedList<>();
      tmp.add(f);
      sum += f;
      while (!line.isEmpty()) {
        int e = line.pollFirst() + (line.peekFirst() == null ? 0 : line.peekFirst());
        tmp.add(e);
        sum += e;
      }
      line = tmp;
      all = sum;
    }
    if (left >= all) return 1;
    return left * 1.0 / all * line.get(query_glass);
  }

}
