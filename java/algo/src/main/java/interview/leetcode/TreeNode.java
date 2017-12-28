package interview.leetcode;

import competition.leetcode.w44.PrintBT;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

/**
 * Created by zzt on 9/4/17.
 * <p>
 * <h3></h3>
 */
public class TreeNode {
    private static PrintBT printBT = new PrintBT();
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public static TreeNode makeTree(String string) {
        String trim = string.trim();
        if (trim.isEmpty() || trim.equals("null")) {
            return null;
        }
        String[] ints = Stream.of(string.split(",")).map(String::trim).toArray(String[]::new);
        if (ints.length == 0) {
            return null;
        }
        TreeNode treeNode = new TreeNode(Integer.parseInt(ints[0]));
        Queue<TreeNode> queue = new ArrayDeque<>(ints.length);
        queue.add(treeNode);
        int i = 1;
        while (!queue.isEmpty() && i < ints.length) {
            TreeNode poll = queue.poll();
            if (ints[i].equals("null")) {
                poll.left = null;
            } else {
                poll.left = new TreeNode(Integer.parseInt(ints[i]));
                queue.add(poll.left);
            }
            i++;
            if (i >= ints.length) break;
            if (ints[i].equals("null")) {
                poll.right = null;
            } else {
                poll.right = new TreeNode(Integer.parseInt(ints[i]));
                queue.add(poll.right);
            }
            i++;
        }
        return treeNode;
    }

    public static TreeNode copy(TreeNode r) {
        if (r == null) {
            return null;
        }
        TreeNode res = new TreeNode(r.val);
        res.left = copy(r.left);
        res.right = copy(r.right);
        return res;
    }

    public static void printBT(TreeNode r) {
        List<List<String>> lists = printBT.printTree(r);
        lists.forEach(System.out::println);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
