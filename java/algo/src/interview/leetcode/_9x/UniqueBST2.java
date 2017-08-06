package interview.leetcode._9x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static competition.leetcode.w31.SubTree.TreeNode;
import static competition.leetcode.w31.SubTree.copy;

/**
 * Created by zzt on 8/5/17.
 * <p>
 * <h3></h3>
 */
public class UniqueBST2 {

    private static List<TreeNode>[] dp = new List[10 + 1];

    static {
        ArrayList<TreeNode> list = new ArrayList<>();
        list.add(null);
        dp[0] = list;
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }
        List<TreeNode> recur = recur(n);
        for (TreeNode node : recur) {
            count(node, n);
        }
        return recur;
    }

    private int count(TreeNode root, int n) {
        if (root == null) {
            return 0;
        }
        root.val = n;
        int lc = count(root.left, n - 1);
        int rc = count(root.right, n - lc - 1);
        return lc + rc + 1;
    }

    private List<TreeNode> recur(int n) {
        if (dp[n] != null) {
            return dp[n];
        }
        List<TreeNode> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<TreeNode> left = recur(i);
            List<TreeNode> right = recur(n - i - 1);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    TreeNode e = new TreeNode(0);
                    e.left = copy(l);
                    e.right = copy(r);
                    res.add(e);
                }
            }

        }
        dp[n] = res;
        return res;
    }

    public static void main(String[] args) {
        UniqueBST2 bst2 = new UniqueBST2();
        System.out.println(bst2.generateTrees(3).size());
    }
}
