package interview.leetcode._2xx._26x;

/**
 * @author zzt
 */
public class UglyNum {

  public boolean isUgly(int num) {
    if (num <=0) {
      return false;
    }
    if (num == 1) {
      return true;
    }
    return num % 2 == 0 ? isUgly(num / 2) :
        num % 3 == 0 ? isUgly(num / 3) :
            num % 5 == 0 && isUgly(num / 5);
  }

  public static void main(String[] args) {
    UglyNum uglyNum = new UglyNum();
    for (int i = 0; i < 20; i++) {
      System.out.println(i + ":" + uglyNum.isUgly(i));
    }
    System.out.println(uglyNum.isUgly(Integer.MAX_VALUE));
    System.out.println(uglyNum.isUgly(Integer.MIN_VALUE));
  }
}
