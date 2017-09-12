package competition.leetcode.w46;

import interview.leetcode.TreeNode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

import static interview.leetcode.TreeNode.makeTree;

/**
 * Created by zzt on 8/20/17.
 * <p>
 * <h3>Index of node in BT</h3>
 * <p>Count index of node by father index and isLeft</p>
 * <p>Different with {@link competition.leetcode.w44.PrintBT}</p>
 *
 * @see competition.leetcode.w44.PrintBT
 */
public class MaxWidthOfBT {

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        HashMap<TreeNode, Integer> index = new HashMap<>();
        mark(root, 0, true, index);

        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        deque.add(null);
        int levelS = -1;
        int max = 1;
        while (true) {
            TreeNode poll = deque.poll();
            if (poll != null) {
                if (poll.left != null) {
                    deque.add(poll.left);
                }
                if (poll.right != null) {
                    deque.add(poll.right);
                }
                Integer i = index.get(poll);
                if (levelS == -1) {
                    levelS = i;
                } else {
                    int m = i - levelS + 1;
                    if (max < m) {
                        max = m;
                    }
                }
            } else {
                if (deque.isEmpty()) {
                    break;
                } else {
                    deque.add(null);
                    levelS = -1;
                }
            }

        }
        return max;
    }

    private void mark(TreeNode root, int fatherIndex, boolean isLeft, HashMap<TreeNode, Integer>
            index) {
        if (root == null) {
            return;
        }
        int i = fatherIndex * 2 + (isLeft ? 0 : 1);
        index.put(root, i);
        mark(root.left, i, true, index);
        mark(root.right, i, false, index);
    }

    public int widthOfBinaryTreeTLE(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);

        int max = 1;
        int levelS = -1;
        int c = 0;
        int d = 1;
        while (!deque.isEmpty()) {
            TreeNode poll = deque.poll();
            c++;
            if (poll == null) {
                deque.add(null);
                deque.add(null);
            } else {
                deque.add(poll.left);
                deque.add(poll.right);
                if (levelS != -1) {
                    if (max < c - levelS + 1) {
                        max = c - levelS + 1;
                    }
                } else {
                    levelS = c;
                }
            }
            if (c == d) {
                if (levelS == -1) {
                    break;
                }
                d *= 2;
                c = 0;
                levelS = -1;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        MaxWidthOfBT bt = new MaxWidthOfBT();
        System.out.println(bt.widthOfBinaryTree(makeTree("1,3,2,5")));
        System.out.println(bt.widthOfBinaryTree(makeTree("1,3,null,5,3")));
        System.out.println(bt.widthOfBinaryTree(makeTree("1,3,2,5,3,null,9")));
        System.out.println(bt.widthOfBinaryTree(makeTree("1,3,2,5,null,null,9,6,7")));
        System.out.println(bt.widthOfBinaryTree(makeTree("1,3,2,5,null,null,9,6,null,null,7")));
    }
}
