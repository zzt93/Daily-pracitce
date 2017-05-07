package competition.leetcode.w31;

/**
 * Created by zzt on 5/7/17.
 * <p>
 * <h3></h3>
 */
public class Squirrel {

    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nuts.length; i++) {
            int[] nut = nuts[i];
            int first = Math.abs(squirrel[0] - nut[0])
                    + Math.abs(squirrel[1] - nut[1])
                    + Math.abs(nut[0] - tree[0])
                    + Math.abs(nut[1] - tree[1]);
            for (int x = 0; x < nuts.length; x++) {
                if (x == i) continue;
                first = first + 2 * (Math.abs(nuts[x][0] - tree[0])
                        + Math.abs(nuts[x][1] - tree[1]));
            }
            if (first < min) {
                min = first;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        Squirrel squirrel = new Squirrel();
        System.out.println(squirrel.minDistance(5, 7, new int[]{2, 2}, new int[]{4, 4}, new int[][]{{3, 0}, {2, 5}}));
    }
}
