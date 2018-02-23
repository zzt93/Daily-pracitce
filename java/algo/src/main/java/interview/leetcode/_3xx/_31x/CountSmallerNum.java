package interview.leetcode._3xx._31x;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zzt on 2/17/18.
 * <p>
 * <h3></h3>
 */
public class CountSmallerNum {

    private static class TNode {
        int c = 0;
        int n;
        TNode left, right;

        TNode(int i) {
            n = i;
        }

        public int getC() {
            return c;
        }

        void inc(int n) {
            if (n < this.n) {
                c++;
            }
        }
    }

    public List<Integer> countSmaller(int[] n) {
        TNode[] nodes = new TNode[n.length];
        TNode now = new TNode(Integer.MIN_VALUE);
        for (int i = 0; i < n.length; i++) {
            nodes[i] = new TNode(n[i]);
            makeTree(now, nodes[i]);
        }
        return Arrays.stream(nodes).mapToInt(TNode::getC).boxed().collect(Collectors.toList());
    }

    private void makeTree(TNode root, TNode node) {
        if (node.n > root.n) {
            if (root.right == null) {
                root.right = node;
            } else {
                makeTree(root.right, node);
            }
        } else {
            root.inc(node.n);
            inc(root.right, node.n);
            if (root.left == null) {
                root.left = node;
            } else {
                makeTree(root.left, node);
            }
        }
    }

    private void inc(TNode root, int n) {
        if (root != null) {
            root.inc(n);
            inc(root.right, n);
            inc(root.left, n);
        }
    }

    public static void main(String[] args) {
        CountSmallerNum c = new CountSmallerNum();
        System.out.println(c.countSmaller(new int[]{26, 78, 27, 100, 33, 67, 90, 23, 66, 5, 38,
                7, 35, 23, 52, 22, 83, 51, 98, 69, 81, 32, 78, 28, 94, 13, 2, 97, 3, 76, 99, 51,
                9, 21, 84, 66, 65, 36, 100, 41}));
        System.out.println(c.countSmaller(new int[]{1, 2, 0}));
        System.out.println(c.countSmaller(new int[]{1, 5, 2, 5, 6, 2, 6, 7, 3}));
        System.out.println(c.countSmaller(new int[]{5, 2, 6, 1}));
        System.out.println(c.countSmaller(new int[]{1, 2, 3, 4, 5, 6}));
        System.out.println(c.countSmaller(new int[]{6, 5, 4, 3, 2, 1}));
    }
}
