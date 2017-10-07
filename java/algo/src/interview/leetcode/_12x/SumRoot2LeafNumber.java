package interview.leetcode._12x;

import interview.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zzt on 10/5/17.
 * <p>
 * <h3></h3>
 */
public class SumRoot2LeafNumber {

    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<Integer> path = new LinkedList<>();
        List<Integer> sum = new ArrayList<>();
        dfs(root, path, sum);
        return sum.stream().mapToInt(Integer::intValue).sum();
    }

    private void dfs(TreeNode root, LinkedList<Integer> path, List<Integer> sum) {
        path.add(root.val);
        if (root.left == null && root.right == null) {
            int s = 0;
            for (Integer aPath : path) {
                s = s * 10 + aPath;
            }
            sum.add(s);
        }
        if (root.left != null) {
            dfs(root.left, path, sum);
        }
        if (root.right != null) {
            dfs(root.right, path, sum);
        }
        path.removeLast();
    }

    public static void main(String[] args) {
        SumRoot2LeafNumber sum = new SumRoot2LeafNumber();
        System.out.println(sum.sumNumbers(TreeNode.makeTree("1,2,3,3,4,null,null,5")));
        System.out.println(sum.sumNumbers(TreeNode.makeTree("1,2,3")));
        System.out.println(sum.sumNumbers(TreeNode.makeTree("1")));
        System.out.println(sum.sumNumbers(TreeNode.makeTree("")));
    }
}
