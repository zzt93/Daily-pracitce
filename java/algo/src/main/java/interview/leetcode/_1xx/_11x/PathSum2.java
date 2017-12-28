package interview.leetcode._1xx._11x;

import interview.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;

import static interview.leetcode.TreeNode.makeTree;

/**
 * Created by zzt on 9/5/17.
 * <p>
 * <h3></h3>
 */
public class PathSum2 {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) {
            return new LinkedList<>();
        }

        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                LinkedList<List<Integer>> res = new LinkedList<>();
                LinkedList<Integer> tmp = new LinkedList<>();
                tmp.addFirst(root.val);
                res.add(tmp);
                return res;
            }
        }
        int aim = sum - root.val;
        if (root.left == null) {
            List<List<Integer>> lists = pathSum(root.right, aim);
            lists.forEach(l -> l.add(0, root.val));
            return lists;
        }
        if (root.right == null) {
            List<List<Integer>> lists = pathSum(root.left, aim);
            lists.forEach(l -> l.add(0, root.val));
            return lists;
        }
        List<List<Integer>> lists = pathSum(root.left, aim);
        lists.addAll(pathSum(root.right, aim));
        lists.forEach(l -> l.add(0, root.val));
        return lists;
    }

    public static void main(String[] args) {
        PathSum2 sum2 = new PathSum2();
        System.out.println(sum2.pathSum(makeTree("5,4,8,11,null,13,4,7,2,null,null,5,1"), 22));
    }
}
