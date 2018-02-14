package competition.leetcode.w68;

/**
 * Created by zzt on 1/21/18.
 * <p>
 * <h3></h3>
 */
public class ToeplitzMatrix {

    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        for (int i = 0; i < matrix[0].length; i++) {
            if (!valid(matrix, 0, i)) {
                return false;
            }
        }
        for (int i = 1; i < m; i++) {
            if (!valid(matrix, i, 0)) {
                return false;
            }
        }
        return true;
    }

    private boolean valid(int[][] m, int x, int y) {
        int aim = m[x][y];
        while (x < m.length && y < m[0].length) {
            if (aim != m[x++][y++]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        ToeplitzMatrix f = new ToeplitzMatrix();
        //        System.out.println(f.isToeplitzMatrix(new int[][]{new int[]{1, 2, 3, 4}, new
        // int[]{5, 1,
        //                2, 3}, new int[]{9, 5, 1, 2}}));
        //        System.out.println(f.isToeplitzMatrix(new int[][]{new int[]{1,2},new int[]{2,
        // 2}}));



    }




}
