package interview.leetcode._19x;

import interview.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;

import static interview.leetcode.TreeNode.makeTree;

/**
 * Created by zzt on 12/5/17.
 * <p>
 * <h3></h3>
 */
public class BTRightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        recur(res, root, 1);
        return res;
    }

    private void recur(List<Integer> res, TreeNode now, int depth) {
        if (now == null) {
            return;
        }
        if (res.size() < depth) {
            res.add(now.val);
        }
        recur(res, now.right, depth + 1);
        recur(res, now.left, depth + 1);
    }

    public static void main(String[] args) {
        BTRightSideView t = new BTRightSideView();
        System.out.println(t.rightSideView(makeTree("1,2,null,3,null,4")));
        System.out.println(t.rightSideView(makeTree("1,null,2,3,null,4")));
        System.out.println(t.rightSideView(makeTree("")));
        System.out.println(t.rightSideView(makeTree("1,2,3,4")));
        System.out.println(t.rightSideView(makeTree("1,2,3,4,5,6")));
        System.out.println(t.rightSideView(makeTree("1,2,3,4,5,6,7")));
    }
}
