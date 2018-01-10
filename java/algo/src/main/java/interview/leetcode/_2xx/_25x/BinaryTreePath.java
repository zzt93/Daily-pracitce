package interview.leetcode._2xx._25x;

import interview.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static interview.leetcode.TreeNode.makeTree;

/**
 * Created by zzt on 1/9/18.
 * <p>
 * <h3></h3>
 */
public class BinaryTreePath {

    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<String> res = new ArrayList<>();
        dfs(res, root, new StringBuilder());
        return res;
    }

    private void dfs(List<String> res, TreeNode root, StringBuilder sb) {
        String s = "->" + root.val;
        sb.append(s);
        if (root.left == null && root.right == null) {
            res.add(sb.toString().substring(2));
        } else {
            if (root.left != null) {
                dfs(res, root.left, sb);
            }
            if (root.right != null) {
                dfs(res, root.right, sb);
            }
        }
        sb.delete(sb.length() - s.length(), sb.length());
    }

    public static void main(String[] args) {
        BinaryTreePath b = new BinaryTreePath();
        System.out.println(b.binaryTreePaths(makeTree("1,2,3,null,5")));
    }
}
