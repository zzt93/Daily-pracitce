package interview.leetcode._9x;

import competition.leetcode.TreeNode;
import competition.leetcode.w44.PrintBT;

import static competition.leetcode.TreeNode.makeTree;

/**
 * Created by zzt on 8/12/17.
 * <p>
 * <h3></h3>
 */
public class RecoverBST {

    public void recoverTree(TreeNode root) {
        TreeNode[] reversed = new TreeNode[4];
        inorder(root, reversed);
        if (reversed[3] == null) {
            swap(reversed, 0, 1);
        } else {
            if (reversed[2].val > reversed[0].val) {
                swap(reversed, 1, 2);
            } else {
                swap(reversed, 0, 3);
            }
        }
    }

    private void swap(TreeNode[] reversed, int i, int j) {
        int t = reversed[i].val;
        reversed[i].val = reversed[j].val;
        reversed[j].val = t;
    }

    private TreeNode[] inorder(TreeNode root, TreeNode[] treeNodes) {
        if (root == null) {
            return null;
        }
        TreeNode[] res = new TreeNode[2];
        TreeNode largest, least;
        if (root.left != null) {
            TreeNode[] left = inorder(root.left, treeNodes);
            largest = left[1];
            res[0] = left[0];
        } else {
            largest = root;
            res[0] = root;
        }
        if (root.right != null) {
            TreeNode[] right = inorder(root.right, treeNodes);
            least = right[0];
            res[1] = right[1];
        } else {
            least = root;
            res[1] = root;
        }

        storeReversed(treeNodes, largest, root);
        storeReversed(treeNodes, root, least);
        return res;
    }

    private void storeReversed(TreeNode[] treeNodes, TreeNode f, TreeNode s) {
        if (f.val > s.val) {
            if (treeNodes[1] == null) {
                treeNodes[0] = f;
                treeNodes[1] = s;
            }
            treeNodes[2] = f;
            treeNodes[3] = s;
        }
    }

    public static void main(String[] args) {
        RecoverBST bst = new RecoverBST();
        TreeNode root = makeTree("3,null,2,null,1");
        bst.recoverTree(root);
        System.out.println(new PrintBT().printTree(root));
    }

}

