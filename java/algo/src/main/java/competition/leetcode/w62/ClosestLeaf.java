package competition.leetcode.w62;

import interview.leetcode.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;

import static interview.leetcode.TreeNode.makeTree;

/**
 * Created by zzt on 12/10/17.
 * <p>
 * <h3>convert to graph, dfs from chosen node</h3>
 * <h3>disjoint set, find shared parent</h3>
 */
public class ClosestLeaf {

    public int findClosestLeaf(TreeNode root, int k) {
        HashSet<TreeNode> leaf = new HashSet<>();
        int[] parent = new int[1001];
        int[] allLevel = new int[1001];
        TreeNode[] aim = new TreeNode[1];
        dfs(root, 0, leaf, allLevel, aim, k, parent);
        if (aim[0].left == null && aim[0].right == null) {
            return aim[0].val;
        } else {
            int val = aim[0].val;
            leaf.remove(aim[0]);
            int min = 1001, minI = 0;
            for (TreeNode l : leaf) {
                int p = parent(l.val, val, parent);
                int tmp;
                if (p == val) {
                    tmp = allLevel[l.val] - allLevel[val];
                } else {
                    tmp = allLevel[l.val] + allLevel[val] - 2 * allLevel[p];
                }
                if (min > tmp) {
                    min = tmp;
                    minI = l.val;
                }
            }
            return minI;
        }
    }

    private int parent(int leaf, int s, int[] parent) {
        LinkedList<Integer> fp = new LinkedList<>();
        while (parent[leaf] != s && parent[leaf] != 0) {
            leaf = parent[leaf];
            fp.addFirst(leaf);
        }
        if (parent[leaf] == s) {
            return s;
        }
        LinkedList<Integer> sp = new LinkedList<>();
        while (parent[s] != 0) {
            s = parent[s];
            sp.addFirst(s);
        }
        int l = Math.min(fp.size(), sp.size());
        int i;
        for (i = 0; i < l; i++) {
            if (!fp.get(i).equals(sp.get(i))) {
                break;
            }
        }
        return fp.get(i - 1);
    }

    private void dfs(TreeNode root, int now, HashSet<TreeNode> leaf, int[] allLevel,
                     TreeNode[] aim,
                     int k, int[] parent) {
        if (root.val == k) {
            aim[0] = root;
        }
        allLevel[root.val] = now;
        if (root.left == null && root.right == null) {
            leaf.add(root);
        }
        if (root.left != null) {
            parent[root.left.val] = root.val;
            dfs(root.left, now + 1, leaf, allLevel, aim, k, parent);
        }
        if (root.right != null) {
            parent[root.right.val] = root.val;
            dfs(root.right, now + 1, leaf, allLevel, aim, k, parent);
        }
    }

    public static void main(String[] args) {
        ClosestLeaf c = new ClosestLeaf();
        System.out.println(c.findClosestLeaf(makeTree("1,3,2"), 1));
        System.out.println(c.findClosestLeaf(makeTree("1"), 1));
        System.out.println(c.findClosestLeaf(makeTree("1,2,3,4,5,null,null,6,7,null,null,8,null,12,null,9,null,11,null,13,null,null,null, 14"), 6));
        System.out.println(c.findClosestLeaf(makeTree("1,2,3,4,null,null,null,5,null,6"), 2));
        System.out.println(c.findClosestLeaf(makeTree("1,2,3,4,5,null,null,6,7,null,null,8,null,null,null,9"), 6));
        System.out.println(c.findClosestLeaf(makeTree("1,2,3,4,5,null,null,6,7,null,null,8"), 6));
        System.out.println(c.findClosestLeaf(makeTree("1,2,3,4,5,null,null,6,7,null,null,8,null,null,null,9,null,10"), 6));
    }
}
