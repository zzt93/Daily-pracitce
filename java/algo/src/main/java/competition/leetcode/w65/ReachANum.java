package competition.leetcode.w65;

/**
 * Created by zzt on 12/31/17.
 * <p>
 * <h3></h3>
 */
public class ReachANum {

    public int reachNumber(int target) {
        target = target < 0 ? -target : target;
        double sqrt = Math.sqrt(target) * 2;
        for (int i = 0; i < sqrt+1; i++) {
            long s = i * (i + 1) / 2 - target;
            if (s >= 0 && s % 2 == 0) {
                return i;
            }
        }
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        ReachANum r = new ReachANum();
//        System.out.println(r.reachNumber(0));
//        System.out.println(r.reachNumber(-3));
//        System.out.println(r.reachNumber(-2));
//        System.out.println(r.reachNumber(-1));
//        System.out.println(r.reachNumber(1));
//        System.out.println(r.reachNumber(2));
//        System.out.println(r.reachNumber(3));
        System.out.println(r.reachNumber(100000000));
    }
}
