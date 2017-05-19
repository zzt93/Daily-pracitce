package competition.leetcode.w31;

import java.util.*;

/**
 * Created by zzt on 5/7/17.
 * <p>
 * <h3></h3>
 */
public class OutOfBoundary {

    public static int c = (int) (Math.pow(10, 9) + 7);

    public int findPaths(int m, int n, int N, int i, int j) {
        if (N == 0) {
            return 0;
        }
        int[][] ints = new int[m][n];
        ints[i][j] = 1;
        //        dfs(i, j, ints, N - 1, m, n);
        loop(i, j, ints, N - 1, m, n);


        int res = 0;
        for (int x = 0; x < ints.length; x++) {
            int[] row = ints[x];
            if (x == 0 || x == m - 1) {
                for (int y = 0; y < row.length; y++) {
                    res = res + row[y] * count(x, y, m, n) % c;
                }
            } else {
                res = res + row[0] * count(x, 0, m, n) % c;
                if (n - 1 != 0) {
                    res += row[n - 1] * count(x, n - 1, m, n) % c;
                }
            }
        }
        return res;
    }

    private void loop(int i, int j, int[][] ints, int N, int m, int n) {
        Comparator<int[]> comparator = (a, b) -> {
            int compare = Integer.compare(a[0], b[0]);
            if (compare == 0) {
                return Integer.compare(a[1], b[1]);
            }
            return compare;
        };
        Set<int[]> set = new TreeSet<>(comparator);
        set.add(new int[]{i, j});
        Set<int[]> tmp = new TreeSet<>(comparator);
        for (int x = 0; x < N; x++) {
            for (int[] xy : set) {
                int[][] surround = surround(xy[0], xy[1], m, n);
                for (int[] s : surround) {
                    if (!tmp.contains(s)) {
                        ints[s[0]][s[1]] = ints[xy[0]][xy[1]];
                        if (s[0] == i && s[1] == j) {
                            ints[i][j]++;
                        }
                        tmp.add(s);
                    } else {
                        ints[s[0]][s[1]] += ints[xy[0]][xy[1]];
                        tmp.add(s);
                    }
                }
            }
            set.clear();
            set.addAll(tmp);
            tmp.clear();
        }
    }

    private int count(int x, int y, int m, int n) {
        int c = 0;
        if (x == 0) {
            c++;
        }
        if (y == 0) {
            c++;
        }
        if (x == m - 1) {
            c++;
        }
        if (y == n - 1) {
            c++;
        }
        return c;
    }

    private void dfs(int i, int j, int[][] ints, int N, int m, int n) {
        if (N <= 0) {
            return;
        }
        int[][] aa = surround(i, j, m, n);
        for (int[] xy : aa) {
            ints[xy[0]][xy[1]]++;
            dfs(xy[0], xy[1], ints, N - 1, m, n);
        }
    }

    private int[][] surround(int i, int j, int m, int n) {
        List<int[]> list = new ArrayList<>(4);
        if (i != 0) {
            list.add(new int[]{i - 1, j});
        }
        if (j != 0) {
            list.add(new int[]{i, j - 1});
        }
        if (i != m - 1) {
            list.add(new int[]{i + 1, j});
        }
        if (j != n - 1) {
            list.add(new int[]{i, j + 1});
        }
        return list.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        OutOfBoundary outOfBoundary = new OutOfBoundary();
        //                System.out.println(outOfBoundary.findPaths(2, 2, 2, 0, 0));
//        System.out.println(outOfBoundary.findPaths(2, 3, 6, 0, 0));
//        for (int i = 0; i < 6; i++) {
//            System.out.println(outOfBoundary.findPaths(2, 3, i + 1, 0, 0));
//        }
        //                System.out.println(outOfBoundary.findPaths(1, 3, 3, 0, 1));
                        System.out.println(outOfBoundary.findPaths(8, 7, 16, 1, 5));
    }
}
