package interview.leetcode._20x;

/**
 * Created by zzt on 12/5/17.
 * <p>
 * <h3>Floyd Cycle detection algorithm</h3>
 */
public class HappyNum {

    private int square(int n) {
        int res = 0;
        while (n > 0) {
            int t = n % 10;
            res += (t * t);
            n /= 10;
        }
        return res;
    }

    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;
        do {
            if (fast == 1) {
                return true;
            }
            slow = square(slow);
            fast = square(square(fast));
        } while (slow != fast);
        return slow == 1;
    }

    public static void main(String[] args) {
        HappyNum happyNum = new HappyNum();
        System.out.println(happyNum.isHappy(10));
        System.out.println(happyNum.isHappy(1234));
        System.out.println(happyNum.isHappy(134));
        System.out.println(happyNum.isHappy(14));
        System.out.println(happyNum.isHappy(1));
        System.out.println(happyNum.isHappy(19));
    }
}
