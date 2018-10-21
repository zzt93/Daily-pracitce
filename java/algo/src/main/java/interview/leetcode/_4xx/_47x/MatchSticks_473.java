package interview.leetcode._4xx._47x;

import java.util.Arrays;

public class MatchSticks_473 {

    public static void main(String[] args) {
//        System.out.println(new MatchSticks_473().makesquare(new int[]{1,1,1,3,3,3,3,4,1,1,1,1,3,6}));
        System.out.println(new MatchSticks_473().makesquare(new int[]{5,5,5,5,16,4,4,4,4,4,3,3,3,3,4}));
    }

    public boolean makesquare(int[] ns) {
        int sum = 0;
        for (int i : ns) sum += i;
        if (sum % 4 != 0 || ns.length < 4) return false;
        int side = sum / 4;
        Arrays.sort(ns);
        if (ns[ns.length - 1] > side) return false;
        return makesquareSub(ns, ns.length - 1, new int[] {side, side, side, side});
    }

    private boolean makesquareSub(int[] nums, int i, int[] s) {
        if (i < 0) return s[0] == 0 && s[1] == 0 && s[2] == 0 && s[3] == 0;

        // time complexity: 4**15
        for (int j = 0; j < s.length; j++) {
            if (nums[i] > s[j]) continue;
            s[j] -= nums[i];
            if (makesquareSub(nums, i - 1, s)) return true;
            s[j] += nums[i];
        }

        return false;
    }
}
