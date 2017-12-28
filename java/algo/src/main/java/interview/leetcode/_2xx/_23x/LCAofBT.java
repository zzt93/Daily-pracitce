package interview.leetcode._2xx._23x;

import interview.leetcode.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;

import static interview.leetcode.TreeNode.makeTree;

/**
 * Created by zzt on 12/28/17.
 * <p>
 * <h3></h3>
 */
public class LCAofBT {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return left == null ? right : right == null ? left : root;
    }

    public TreeNode lowestCommonAncestorSlow(TreeNode root, TreeNode p, TreeNode q) {
        HashMap<TreeNode, TreeNode> parent = new HashMap<>();
        dfs(parent, root);
        LinkedList<TreeNode> p1 = parentList(parent, p);
        LinkedList<TreeNode> q1 = parentList(parent, q);
        TreeNode res = root;
        while (!p1.isEmpty() && !q1.isEmpty() && p1.peekLast().val == q1.peekLast().val) {
            res = p1.peekLast();
            p1.removeLast();
            q1.removeLast();
        }
        return res;
    }

    private LinkedList<TreeNode> parentList(HashMap<TreeNode, TreeNode> parent, TreeNode p) {
        LinkedList<TreeNode> res = new LinkedList<>();
        while (p != null) {
            res.add(p);
            p = parent.get(p);
        }
        return res;
    }

    private void dfs(HashMap<TreeNode, TreeNode> parent, TreeNode root) {
        if (root.left != null) {
            parent.put(root.left, root);
            dfs(parent, root.left);
        }
        if (root.right != null) {
            parent.put(root.right, root);
            dfs(parent, root.right);
        }
    }

    public static void main(String[] args) {
        LCAofBT bt = new LCAofBT();
        System.out.println(bt.lowestCommonAncestor(makeTree("1,2"), new TreeNode(1), new
                TreeNode(2)));
        System.out.println(bt.lowestCommonAncestor(makeTree("1,2,3,4,5,6"), new TreeNode(4), new
                TreeNode(5)));
        System.out.println(bt.lowestCommonAncestor(makeTree("1,2,3,4,5,6"), new TreeNode(4), new
                TreeNode(6)));
    }
}
