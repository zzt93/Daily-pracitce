package interview.leetcode._2xx._23x;

import interview.leetcode.TreeNode;

import static interview.leetcode.TreeNode.makeTree;

/**
 * Created by zzt on 12/26/17.
 * <p>
 * <h3></h3>
 */
public class KSmallestInBST {

    private TreeNode res = new TreeNode(0);

    public int kthSmallest(TreeNode root, int k) {
        try {
            inorder(root, 0, k);
        } catch (Exception e) {
            return res.val;
        }
        return 0;
    }

    private int inorder(TreeNode root, int now, int k) throws Exception {
        if (root == null) {
            return 0;
        }
        int left = inorder(root.left, now, k);
        if (left + now == k - 1) {
            res.val = root.val;
            throw new Exception();
        }
        int right = inorder(root.right, now + left + 1, k);
        return left + 1 + right;
    }

    public static void main(String[] args) {
        KSmallestInBST k = new KSmallestInBST();
        System.out.println(k.kthSmallest(makeTree("1"), 1));
        System.out.println(k.kthSmallest(makeTree("6,4,8,2,5,7,9,1,3"), 1));
        System.out.println(k.kthSmallest(makeTree("6,4,8,2,5,7,9,1,3"), 2));
        System.out.println(k.kthSmallest(makeTree("6,4,8,2,5,7,9,1,3"), 3));
        System.out.println(k.kthSmallest(makeTree("6,4,8,2,5,7,9,1,3"), 4));
        System.out.println(k.kthSmallest(makeTree("6,4,8,2,5,7,9,1,3"), 5));
        System.out.println(k.kthSmallest(makeTree("6,4,8,2,5,7,9,1,3"), 6));
        System.out.println(k.kthSmallest(makeTree("6,4,8,2,5,7,9,1,3"), 7));
        System.out.println(k.kthSmallest(makeTree("6,4,8,2,5,7,9,1,3"), 8));
        System.out.println(k.kthSmallest(makeTree("6,4,8,2,5,7,9,1,3"), 9));
    }
}
