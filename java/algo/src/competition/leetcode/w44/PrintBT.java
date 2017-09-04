package competition.leetcode.w44;

import competition.leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static competition.leetcode.TreeNode.makeTree;

/**
 * Created by zzt on 8/6/17.
 * <p>
 * <h3>BFS</h3>
 * <p>Count the index of node and set line by line</p>
 * <h3>DFS</h3>
 * <p>Just calculate the right position of cell, not care index of node</p>
 */
public class PrintBT {

    public List<List<String>> printTree(TreeNode root) {
        int depth = dep(root);
        List<List<String>> res = new ArrayList<>(depth);
        int size = (int) Math.pow(2, depth) - 1;
        String[] strings = new String[size];
        Arrays.fill(strings, "");
        for (int i = 0; i < depth; i++) {
            res.add(new ArrayList<>(Arrays.asList(strings)));
        }
        if (root == null) {
            return res;
        }
        //                bfs(root, depth, res);
        dfs(root, 0, 0, size, res);
        return res;
    }

    private void dfs(TreeNode root, int depth, int s, int e, List<List<String>> res) {
        if (root == null) {
            return;
        }
        int mid = (s + e) / 2;
        res.get(depth).set(mid, "" + root.val);
        dfs(root.left, depth + 1, s, mid, res);
        dfs(root.right, depth + 1, mid + 1, e, res);
    }

    //    private void bfs(TreeNode root, int depth, List<List<String>> res) {
    //        Deque<TreeNode> queue = new LinkedList<>();
    //        queue.addLast(root);
    //        int r = 0, sonI = 0;
    //        int sum = 1;
    //        while (r != depth) {
    //            TreeNode poll = queue.pollFirst();
    //            sonI++;
    //            if (poll != null) {
    //                res.get(r).set(col(depth, r, sonI - 1), "" + poll.val);
    //                queue.addLast(poll.left);
    //                queue.addLast(poll.right);
    //            } else {
    //                queue.addLast(null);
    //                queue.addLast(null);
    //            }
    //            if (sonI == sum) {
    //                r++;
    //                sum *= 2;
    //                sonI = 0;
    //            }
    //        }
    //    }

    //    private int col(int d, int r, int sonI) {
    //        int unit = (int) (Math.pow(2, d - r) - 1);
    //        return unit / 2 + sonI * unit + sonI;
    //    }

    private int dep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(dep(root.left), dep(root.right)) + 1;
    }

    public static void main(String[] args) {
        PrintBT printBT = new PrintBT();
        System.out.println(printBT.printTree(makeTree("3,null,30,10,null,null,15,null,45")));
        System.out.println(printBT.printTree(makeTree("")));
        System.out.println(printBT.printTree(makeTree("1,2")));
        System.out.println(printBT.printTree(makeTree("1,2,3,null,4")));
        System.out.println(printBT.printTree(makeTree("1,2,3,4,null,null,null,5,null")));
    }
}
