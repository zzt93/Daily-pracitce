package interview.leetcode._9x;

import static competition.leetcode.w31.SubTree.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zzt on 8/3/17.
 * <p>
 * <h3></h3>
 */
public class InorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode now = root;
        while (now != null || !stack.isEmpty()) {
            while (now != null) {
                stack.push(now);
                now = now.left;
            }
            now = stack.pop();
            res.add(now.val);
            now = now.right;
        }
        return res;
    }

    public static void main(String[] args) {
        InorderTraversal traversal = new InorderTraversal();
        System.out.println(traversal.inorderTraversal(makeTree("1,null,2,3")));
        System.out.println(traversal.inorderTraversal(makeTree("1")));
        System.out.println(traversal.inorderTraversal(makeTree("1,2,null,3,null,4,null,5,6,null,null,7,null,8,null")));
        System.out.println(traversal.inorderTraversal(makeTree("1,2,null,3,null,4,5,6,null,null,7,null,8,null")));
        System.out.println(traversal.inorderTraversal(makeTree("null")));
        System.out.println(traversal.inorderTraversal(makeTree("1,null,2,null,3,null,4,null,5")));
    }
}
