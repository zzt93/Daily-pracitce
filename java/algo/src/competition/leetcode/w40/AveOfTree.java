package competition.leetcode.w40;

import interview.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by zzt on 7/9/17.
 * <p>
 * <h3></h3>
 */
public class AveOfTree {

    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<Double> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        queue.add(root);
        TreeNode split = new TreeNode(0);
        long sum = 0, c = 0;
        while (!queue.isEmpty()) {
            queue.add(split);
            while (queue.peek() != split) {
                TreeNode head = queue.poll();
                if (head.left != null) {
                    queue.add(head.left);
                }
                if (head.right != null) {
                    queue.add(head.right);
                }
                sum += head.val;
                c++;
            }
            queue.poll();
            res.add(sum * 1.0 / c);
            sum = c = 0;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new AveOfTree().averageOfLevels(TreeNode.makeTree("3,9,20,15,7,-1")));
        System.out.println(new AveOfTree().averageOfLevels(null));
    }
}
