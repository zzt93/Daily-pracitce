package interview.leetcode;

import java.util.ArrayList;
import java.util.Collections;

public class TwoSum {

    class IndexedNum implements Comparable<IndexedNum> {
        int n;
        int i;

        public IndexedNum(int n, int i) {
            this.n = n;
            this.i = i;
        }

        public int compareTo(IndexedNum i) {
            return new Integer(n).compareTo(i.n);
        }

    }

    public int[] twoSum(int[] nums, int target) {
        ArrayList<IndexedNum> list = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            list.add(new IndexedNum(nums[i], i));
        }
        Collections.sort(list);
        for (int i = 0; i < nums.length; i++) {
            int aim = target - nums[i];
            int res = Collections.binarySearch(list, new IndexedNum(aim, -1));
            if (res > 0) {
                return new int[]{i, list.get(res).i};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        new TwoSum().twoSum(new int[]{3, 2, 4}, 6);
    }
}