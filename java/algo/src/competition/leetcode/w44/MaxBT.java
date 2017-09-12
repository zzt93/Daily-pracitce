package competition.leetcode.w44;


import interview.leetcode.TreeNode;

/**
 * Created by zzt on 8/6/17.
 * <p>
 * <h3></h3>
 */
public class MaxBT {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return recur(nums, 0, nums.length);
    }

    private TreeNode recur(int[] n, int s, int e) {
        if (s >= e) {
            return null;
        }
        int max = Integer.MIN_VALUE;
        int maxI = -1;
        for (int i = s; i < e; i++) {
            if (max < n[i]) {
                max = n[i];
                maxI = i;
            }
        }
        TreeNode res = new TreeNode(max);
        res.left = recur(n, s, maxI);
        res.right = recur(n, maxI + 1, e);
        return res;
    }

    public static void main(String[] args) {
        MaxBT maxBT = new MaxBT();
        System.out.println(maxBT.constructMaximumBinaryTree(new int[]{3, 2, 1, 6, 0, 5}));
    }
}
