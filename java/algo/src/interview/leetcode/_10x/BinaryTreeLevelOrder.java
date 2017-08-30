package interview.leetcode._10x;

import interview.leetcode.BFS;

import java.util.ArrayList;
import java.util.List;

import static competition.leetcode.w31.SubTree.TreeNode;
import static competition.leetcode.w31.SubTree.makeTree;

/**
 * Created by zzt on 8/29/17.
 * <p>
 * <h3></h3>
 */
public class BinaryTreeLevelOrder {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        list.add(new ArrayList<>());
        final int[] c = {0};
        BFS.bfs(treeNode -> {
            if (treeNode == null) {
                list.add(new ArrayList<>());
                c[0]++;
            } else {
                list.get(c[0]).add(treeNode.val);
            }
        }, root);
        list.remove(list.size() - 1);
        return list;
    }

    public static void main(String[] args) {
        BinaryTreeLevelOrder order = new BinaryTreeLevelOrder();
        System.out.println(order.levelOrder(makeTree("3,9,20,null,null,15,7")));
        System.out.println(order.levelOrder(makeTree("")));
    }
}
