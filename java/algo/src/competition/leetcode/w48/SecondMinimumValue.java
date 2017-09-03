package competition.leetcode.w48;

import static competition.leetcode.w31.SubTree.TreeNode;
import static competition.leetcode.w31.SubTree.makeTree;

/**
 * Created by zzt on 9/3/17.
 * <p>
 * <h3></h3>
 */
public class SecondMinimumValue {

    public int findSecondMinimumValue(TreeNode root) {
        if (root == null || root.left == null) {
            return -1;
        }
        int l = root.left.val;
        int r = root.right.val;
        if (l == root.val && r == root.val) {
            int ls = findSecondMinimumValue(root.left);
            int rs = findSecondMinimumValue(root.right);
            if (ls == -1) {
                return rs;
            }
            if (rs == -1) {
                return ls;
            }
            return Math.min(ls, rs);
        }
        if (l == root.val) {
            int rs = r;
            int ls = findSecondMinimumValue(root.left);
            if (ls == -1) {
                return rs;
            }
            return Math.min(ls, rs);
        } else {
            // r == root.val
            int rs = findSecondMinimumValue(root.right);
            int ls = l;
            if (rs == -1) {
                return ls;
            }
            return Math.min(ls, rs);
        }
    }

    public static void main(String[] args) {
        SecondMinimumValue val = new SecondMinimumValue();
        System.out.println(val.findSecondMinimumValue(makeTree("1,1,3,1,1,3,4,3,1,1,1,3,8,4,8,3,3,1,6,2,1")));
        System.out.println(val.findSecondMinimumValue(makeTree("2,2,2,2,5")));
        System.out.println(val.findSecondMinimumValue(makeTree("2,2,5,null,null,5,7")));
        System.out.println(val.findSecondMinimumValue(makeTree("2,2,2")));
        System.out.println(val.findSecondMinimumValue(makeTree("2,2,2,2,2")));
        System.out.println(val.findSecondMinimumValue(makeTree("2,2,2,2,2,2,2")));
        System.out.println(val.findSecondMinimumValue(makeTree("2,2,2,2,2,null,null,2,5")));
    }
}
