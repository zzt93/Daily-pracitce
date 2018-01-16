package interview.leetcode._2xx._27x;

/**
 * @author zzt
 */
public class FirstBadVersion {

  public int firstBadVersion(int n) {
    long s = 1, e = (long) n+1;
    while (s < e) {
      long mid = (s+e)/2;
      if (isBadVersion((int) mid)) {
        e = mid;
      } else {
        s = mid+1;
      }
    }
    return (int) e;
  }

  boolean isBadVersion(int version) {
    return version >= 2147483647;
  }

  public static void main(String[] args) {
    FirstBadVersion f = new FirstBadVersion();
    System.out.println(f.firstBadVersion(2147483647));
  }
}
