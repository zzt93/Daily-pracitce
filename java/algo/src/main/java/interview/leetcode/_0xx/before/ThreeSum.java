package interview.leetcode._0xx.before;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


public class ThreeSum {
    class IntArray {
        int[] i;

        public IntArray(int[] i) {
            this.i = i;
        }

        public int hashCode() {
            return Arrays.hashCode(i);
        }


        @Override
        public boolean equals(Object obj) {
            IntArray intArray = obj instanceof IntArray ? ((IntArray) obj) : null;
            return intArray != null && Arrays.equals(i, intArray.i);
        }


    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        HashSet<IntArray> dupCheck = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (n > 0) {
                break;
            }
            List<int[]> intsList = twoSum(Arrays.copyOfRange(nums, i + 1, nums.length), -n);
            for (int[] ints : intsList) {
                IntArray t = new IntArray(ints);
                if (dupCheck.contains(t)) {
                    continue;
                } else {
                    dupCheck.add(t);
                }
                List<Integer> tmp = new ArrayList<>();
                tmp.add(n);
                tmp.add(ints[0]);
                tmp.add(ints[1]);
                res.add(tmp);
            }
        }
        return res;
    }

    public List<int[]> twoSum(int[] nums, int target) {
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            int t = target - n;
            if (Arrays.binarySearch(nums, i + 1, nums.length, t) > 0) {
                res.add(new int[]{n, t});
            }
        }
        return res;
    }
}