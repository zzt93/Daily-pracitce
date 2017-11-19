package competition.leetcode.w59;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zzt on 11/19/17.
 * <p>
 * <h3></h3>
 */
public class SelfDividingNum {

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (isN(i)) {
                res.add(i);
            }
        }
        return res;
    }

    private boolean isN(int i) {
        int r, tmp = i;
        while (tmp > 0) {
            r = tmp % 10;
            if (r == 0 || i % r != 0) {
                return false;
            }
            tmp /= 10;
        }
        return true;
    }

    public static void main(String[] args) {
        SelfDividingNum s = new SelfDividingNum();
        System.out.println(s.selfDividingNumbers(1, 22));
    }
}
