package competition.leetcode.w86;

/**
 * @author zzt
 */
public class MagicSquare {

  public int numMagicSquaresInside(int[][] grid) {
    int res = 0;
    int m = grid.length, n = grid[0].length;
    for (int r = 0; r <= m - 3; r++) {
      for (int c = 0; c <= n - 3; c++) {
        if (magic(grid, r, c)) res++;
      }
    }
    return res;
  }

  private boolean magic(int[][] grid, int r, int c) {
    int[] m = new int[10];
    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 3; y++) {
        int i = grid[r + x][c + y];
        if (i < 1 || i > 9) return false;
        if (m[i] != 0) return false;
        m[i] = 1;
      }
    }
    int[] f = grid[r], s= grid[r+1];
    if (f[c] + f[c+1] + f[c+2] == 15 && s[c] + s[c+1] + s[c+2] == 15
        && grid[r][c] + grid[r+1][c] + grid[r+2][c]== 15 && grid[r][c+1] + grid[r+1][c+1] + grid[r+2][c+1]== 15
        && grid[r][c] + grid[r+1][c+1] + grid[r+2][c+2]== 15 && grid[r][c+2] + grid[r+1][c+1] + grid[r+2][c]== 15) {
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    MagicSquare m = new MagicSquare();
    System.out.println(m.numMagicSquaresInside(
        new int[][]{new int[]{4, 3, 8, 4}, new int[]{9, 5, 1, 9}, new int[]{2, 7, 6, 2}}));
  }

}
