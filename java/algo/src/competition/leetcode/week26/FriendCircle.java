package competition.leetcode.week26;

import java.util.HashSet;

/**
 * Created by zzt on 4/2/17.
 * <p>
 * <h3></h3>
 */
public class FriendCircle {

    public int findCircleNum(int[][] M) {
        HashSet<Integer> visited = new HashSet<>(M.length);
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited.contains(i)) {
                continue;
            }
            visit(visited, M, i);
            count++;
        }
        return count;
    }

    private void visit(HashSet<Integer> visited, int[][] m, int i) {
        visited.add(i);
        for (int y = 0; y < m[i].length; y++) {
            if (m[i][y] == 1 && !visited.contains(y)) {
                visit(visited, m, y);
            }
        }
    }

    public static void main(String[] args) {
        int[][] ints = new int[1][];
        ints[0] = new int[]{1};
        FriendCircle circle = new FriendCircle();
        System.out.println(circle.findCircleNum(ints));
    }
}
