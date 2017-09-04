package competition.leetcode.w48;

import competition.leetcode.TreeNode;
import competition.leetcode.w44.PrintBT;

import static competition.leetcode.TreeNode.makeTree;

/**
 * Created by zzt on 9/3/17.
 * <p>
 * <h3></h3>
 */
public class TrimBST {

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }
        if (root.val < L) {
            return trimBST(root.right, L, R);
        }
        if (root.val > R) {
            return trimBST(root.left, L, R);
        }
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }

    public static void main(String[] args) {
        TrimBST trimBST = new TrimBST();
        PrintBT printBT = new PrintBT();
        System.out.println(printBT.printTree(trimBST.trimBST(makeTree("1,0,2"), 1, 2)));
        System.out.println(printBT.printTree(trimBST.trimBST(makeTree("3,0,4,null,2,null,null,1")
                , 1, 3)));
    }
}
