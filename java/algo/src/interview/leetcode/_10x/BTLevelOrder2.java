package interview.leetcode._10x;

import competition.leetcode.TreeNode;
import interview.leetcode.BFS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static competition.leetcode.TreeNode.makeTree;

/**
 * Created by zzt on 9/4/17.
 * <p>
 * <h3></h3>
 */
public class BTLevelOrder2 {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        BFS.bfs(treeNode -> {
            if (treeNode == null) {
                res.add(new ArrayList<>());
            } else {
                res.get(res.size() - 1).add(treeNode.val);
            }
        }, root);
        res.remove(res.size() - 1);
        int size = res.size();
        for (int i = 0; i < size / 2; i++) {
            Collections.swap(res, i, size - i - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        BTLevelOrder2 order = new BTLevelOrder2();
        System.out.println(order.levelOrderBottom(makeTree("3,9,20,null,null,15,7")));
        System.out.println(order.levelOrderBottom(makeTree("")));
    }
}
