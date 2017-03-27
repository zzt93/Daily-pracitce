package competition.leetcode.week25;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by zzt on 3/26/17.
 * <p>
 * <h3></h3>
 */
public class PerfectNum {

    public boolean checkPerfectNumber(int num) {
        if (num == 1) {
            return false;
        }
        int limit = (int) Math.sqrt(num);
        List<Integer> list = new LinkedList<>();
        for (int i = 2; i <= limit; i++) {
            if (num / i * i == num) {
                list.add(i);
                list.add(num / i);
            }
        }
        int res = 1;
        for (Integer integer : list) {
            res += integer;
        }
        return res == num;
    }

    public static void main(String[] args) {
        PerfectNum perfectNum = new PerfectNum();
        System.out.println(perfectNum.checkPerfectNumber(28));
        System.out.println(perfectNum.checkPerfectNumber(2));
        System.out.println(perfectNum.checkPerfectNumber(1));
    }
}
