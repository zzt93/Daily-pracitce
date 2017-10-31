package interview.leetcode._14x;

import interview.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zzt on 11/1/17.
 * <p>
 * <h3></h3>
 */
public class BTTraversal {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode t = stack.removeLast();
            if (t == null) {
                continue;
            }
            list.add(t.val);
            stack.add(t.right);
            stack.add(t.left);
        }
        return list;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        HashSet<TreeNode> map = new HashSet<>();
        while (!stack.isEmpty()) {
            TreeNode now = stack.removeLast();
            if (now == null) {
                continue;
            }
            if (map.contains(now)) {
                list.add(now.val);
            } else {
                map.add(now);
                stack.add(now);
                stack.add(now.right);
                stack.add(now.left);
            }
        }
        return list;
    }

}
