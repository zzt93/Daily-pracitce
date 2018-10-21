package competition.mis;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoveCoin {

  public static int getMinimum(List<String> board, int k) {
    int n = board.size();
    int m = board.get(0).length();

    int min = Integer.MAX_VALUE;

    boolean[][] b = new boolean[n][m];
    boolean empty = true;
    int[][] l = new int[n][m];
    for (int i = 0; i < board.size(); i++) {
      char[] chars = board.get(i).toCharArray();
      for (int x = 0; x < chars.length; x++) {
        b[i][x] = chars[x] == 'o';
        empty = empty && !b[i][x];
        int c = 0;
        if (b[i][x]) c++;
        if (i > 0 && x > 0) l[i][x] = c + l[i-1][x] + l[i][x-1] - l[i-1][x-1];
        else if (i > 0) l[i][x] = c + l[i-1][x];
        else if (x > 0) l[i][x] = c + l[i][x-1];
        else l[i][x] = c;
        if (l[i][x] == k) {
          min = Math.min(min, n - i - 1 + m - x - 1);
        }
      }
    }
    if (k == 0) {
      if (empty) return 0;
      return Math.min(m, n);
    }


    int[][] r = new int[n][m];
    for (int i = 0; i < n; i++) {
      for (int x = m-1; x >= 0; x--) {
        int c = 0;
        if (b[i][x]) c++;
        if (i > 0 && x < m-1) r[i][x] = c + r[i-1][x] + r[i][x+1] - r[i-1][x+1];
        else if (i > 0) r[i][x] = c + r[i-1][x];
        else if (x < m-1) r[i][x] = c + r[i][x+1];
        else r[i][x] = c;
        if (r[i][x] == k) {
          min = Math.min(min, x + n - i - 1);
        }
      }
    }
    int[][] ld = new int[n][m];
    for (int i = n - 1; i >= 0; i--) {
      for (int x = 0; x < m; x++) {
        int c = 0;
        if (b[i][x]) c++;
        if (i < n - 1 && x > 0) ld[i][x] = c + ld[i+1][x] + ld[i][x-1] - ld[i+1][x-1];
        else if (i < n-1) ld[i][x] = c + ld[i+1][x];
        else if (x > 0) ld[i][x] = c + ld[i][x-1];
        else ld[i][x] = c;
        if (ld[i][x] == k) {
          min = Math.min(min, i + m - x - 1);
        }
      }
    }
    int[][] rd = new int[n][m];
    for (int i = n - 1; i >= 0; i--) {
      for (int x = m-1; x >= 0; x--) {
        int c = 0;
        if (b[i][x]) c++;
        if (i < n-1 && x < m-1) rd[i][x] = c + rd[i+1][x] + rd[i][x+1] - rd[i+1][x+1];
        else if (i < n-1) rd[i][x] = c + rd[i+1][x];
        else if (x < m-1) rd[i][x] = c + rd[i][x+1];
        else rd[i][x] = c;
        if (rd[i][x] == k) {
          min = Math.min(min, i + x);
        }
      }
    }

    return min == Integer.MAX_VALUE ? -1 : min;
  }

  public static void main(String[] args){
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    int m = in.nextInt();
    in.nextLine();
    List<String> v = new ArrayList<String>();
    for(int i = 0 ;i < n;i++){
      String temp = in.nextLine();
      v.add(temp);
    }

    int k = in.nextInt();
    System.out.println(getMinimum(v, k));
  }
}