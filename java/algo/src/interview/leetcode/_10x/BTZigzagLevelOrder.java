package interview.leetcode._10x;

import java.util.Collections;
import java.util.List;

import static competition.leetcode.w31.SubTree.TreeNode;
import static competition.leetcode.w31.SubTree.makeTree;

/**
 * Created by zzt on 8/30/17.
 * <p>
 * <h3></h3>
 */
public class BTZigzagLevelOrder {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        BinaryTreeLevelOrder order = new BinaryTreeLevelOrder();
        List<List<Integer>> lists = order.levelOrder(root);
        for (int i = 1; i < lists.size(); i+=2) {
            List<Integer> integers = lists.get(i);
            Collections.reverse(integers);
        }
        return lists;
    }

    public static void main(String[] args) {
        BTZigzagLevelOrder order = new BTZigzagLevelOrder();
        System.out.println(order.zigzagLevelOrder(makeTree("")));
        System.out.println(order.zigzagLevelOrder(makeTree("3,9,20,null,null,15,7")));
    }
}
