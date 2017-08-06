package competition.leetcode.w44;

import java.util.*;

import static competition.leetcode.w31.SubTree.TreeNode;
import static competition.leetcode.w31.SubTree.makeTree;

/**
 * Created by zzt on 8/6/17.
 * <p>
 * <h3></h3>
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
        // bfs
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addLast(root);
        int r = 0, sonI = 0;
        int sum = 1;
        while (r != depth) {
            TreeNode poll = queue.pollFirst();
            sonI++;
            if (poll != null) {
                res.get(r).set(col(depth, r, sonI - 1), "" + poll.val);
                queue.addLast(poll.left);
                queue.addLast(poll.right);
            } else {
                queue.addLast(null);
                queue.addLast(null);
            }
            if (sonI == sum) {
                r++;
                sum *= 2;
                sonI = 0;
            }
        }
        return res;
    }

    private int col(int d, int r, int sonI) {
        int unit = (int) (Math.pow(2, d - r) - 1);
        return unit / 2 + sonI * unit + sonI;
    }

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
