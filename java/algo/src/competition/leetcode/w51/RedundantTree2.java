package competition.leetcode.w51;

import java.util.Arrays;

/**
 * Created by zzt on 9/24/17.
 * <p>
 * <h3>Check circles by disjoint set: if two nodes of an edge has same father, there is circle</h3>
 */
public class RedundantTree2 {


    private int[] parent = new int[2222];

    public int[] findRedundantConnection(int[][] edges) {
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int[] edge : edges) {
            int a = findParent(edge[0]);
            int b = findParent(edge[1]);
            if (a == b) {
                return edge;
            }
            parent[a] = b;
        }
        return new int[2];
    }

    private int findParent(int i) {
        while (i != parent[i]) {
            i = parent[i];
        }
        return i;
    }

    public static void main(String[] args) {
        RedundantTree2 redundantTree = new RedundantTree2();
        System.out.println(Arrays.toString(redundantTree.findRedundantConnection(new int[][]{{1,
                2}, {1, 3}, {2, 3}})));
        System.out.println(Arrays.toString(redundantTree.findRedundantConnection(new int[][]{{1,
                2}, {1, 3}, {3, 1}})));
        System.out.println(Arrays.toString(redundantTree.findRedundantConnection(new int[][]{{1,
                2}, {1, 3}, {3, 4}, {1, 4}})));
        System.out.println(Arrays.toString(redundantTree.findRedundantConnection(new int[][]{{1,
                3}, {3, 4}, {1, 4}, {1, 2}})));
        System.out.println(Arrays.toString(redundantTree.findRedundantConnection(new int[][]{{1,
                3}, {3, 2}, {1, 4}, {4, 2}})));
    }
}
