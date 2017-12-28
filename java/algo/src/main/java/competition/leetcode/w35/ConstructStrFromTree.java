package competition.leetcode.w35;

import interview.leetcode.TreeNode;

/**
 * Created by zzt on 6/4/17.
 * <p>
 * <h3></h3>
 */
public class ConstructStrFromTree {


    public static final String NULL = "()";

    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        return dfs(t);
    }

    private String dfs(TreeNode t) {
        if (t == null) {
            return NULL;
        }
        String left = dfs(t.left);
        String right = dfs(t.right);
        String leftStr;
        if (left == NULL && right != NULL) {
            leftStr = "()";
        } else if (left != NULL) {
            leftStr = "(" + left + ")";
        } else {
            leftStr = "";
        }
        return t.val + leftStr
                + (right == NULL ? "" : "(" + right + ")");
    }

    public static void main(String[] args) {
        ConstructStrFromTree tree = new ConstructStrFromTree();
        System.out.println(tree.tree2str(null));
        System.out.println(tree.tree2str(TreeNode.makeTree("1,2,3,4")));
        System.out.println(tree.tree2str(TreeNode.makeTree("1,2,null,4")));
        System.out.println(tree.tree2str(TreeNode.makeTree("1,2,3,null,4")));
        System.out.println(tree.tree2str(TreeNode.makeTree("1,2,3,null,4,5,6,null,7")));
    }
}
