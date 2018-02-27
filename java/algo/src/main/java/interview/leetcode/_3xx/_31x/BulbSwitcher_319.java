package interview.leetcode._3xx._31x;

/**
 * @author zzt
 */
public class BulbSwitcher_319 {

  public int bulbSwitch(int n) {
    int r = 0;
    for (int i = 1; i*i <= n; i++) {
      r++;
    }
    return r;
  }

  public static void main(String[] args) {
    BulbSwitcher_319 b = new BulbSwitcher_319();
    System.out.println(b.bulbSwitch(10000000));
    System.out.println(b.bulbSwitch(3));
  }

}
