package interview.leetcode._2xx._24x;

/**
 * Created by zzt on 1/4/18.
 * <p>
 * <h3></h3>
 */
public class SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        return matrix.length != 0 && search(matrix, 0, 0, matrix.length - 1, matrix[0].length -
                1, target);
    }

    private boolean search(int[][] matrix, int x, int y, int m, int n, int target) {
        if (x > m || y > n || x == matrix.length || y == matrix[0].length || x < 0 || y < 0) return false;
        int mi = (x + m) / 2;
        int mj = (y + n) / 2;
        if (matrix[mi][mj] > target) {
            return search(matrix, x, y, mi - 1, mj - 1, target)
                    || search(matrix, mi, y, m, mj - 1, target)
                    || search(matrix, x, mj, mi - 1, n, target);
        } else if (matrix[mi][mj] < target) {
            return search(matrix, mi + 1, mj + 1, m, n, target)
                    || search(matrix, mi + 1, y, m, mj, target)
                    || search(matrix, x, mj + 1, mi, n, target);
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        SearchMatrix s = new SearchMatrix();
        System.out.println(s.searchMatrix(new int[][]{new int[]{1, 3, 5}}, -1));
        System.out.println(s.searchMatrix(new int[][]{new int[]{1, 4, 7, 11, 15}, new int[]{2, 5,
                8, 12, 19}, new int[]{3, 6, 9, 16, 22}, new int[]{10, 13, 14, 17, 24}, new
                int[]{18, 21, 23, 26, 30}}, 20));
        System.out.println(s.searchMatrix(new int[][]{new int[]{1, 4, 7, 11, 15}, new int[]{2, 5,
                8, 12, 19}, new int[]{3, 6, 9, 16, 22}, new int[]{10, 13, 14, 17, 24}}, 21));

        System.out.println(s.searchMatrix(new int[][]{new int[]{1, 3, 5}}, 1));
        System.out.println(s.searchMatrix(new int[][]{new int[]{1, 4, 7, 11, 15}, new int[]{2, 5,
                8, 12, 19}, new int[]{3, 6, 9, 16, 22}, new int[]{10, 13, 14, 17, 24}, new
                int[]{18, 21, 23, 26, 30}}, 21));
        System.out.println(s.searchMatrix(new int[][]{new int[]{1, 4, 7, 11, 15}, new int[]{2, 5,
                8, 12, 19}, new int[]{3, 6, 9, 16, 22}, new int[]{10, 13, 14, 17, 24}}, 22));
        System.out.println(s.searchMatrix(new int[][]{new int[]{1, 4, 7, 11, 15}, new int[]{2, 5,
                8, 12, 19}, new int[]{3, 6, 9, 16, 22}, new int[]{10, 13, 14, 17, 24}}, 2));
        System.out.println(s.searchMatrix(new int[][]{new int[]{1, 4, 7, 11, 15}, new int[]{2, 5,
                8, 12, 19}, new int[]{3, 6, 9, 16, 22}, new int[]{10, 13, 14, 17, 24}}, 1));
        System.out.println(s.searchMatrix(new int[][]{new int[]{1, 4, 7, 11, 15}, new int[]{2, 5,
                8, 12, 19}, new int[]{3, 6, 9, 16, 22}, new int[]{10, 13, 14, 17, 24}, new
                int[]{18, 21, 23, 26, 30}}, 5));
    }
}
