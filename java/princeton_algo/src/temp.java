/**
 * Created by zzt on 2/6/15.
 */
/**
 * Created by zzt on 2/1/15.
 */
public class temp  {
    private WeightedQuickUnionUF quickFindUF;
    private final int head;
    private final int tail;
    private final int size;
    private boolean[][] sites;
    private int openSites = 0;

    public temp(int N) {               // create N-by-N grid, with all sites blocked
        if (N <= 0) {
            throw new IllegalArgumentException("N is "+N);
        }
        sites = new boolean[N+1][N+1];
        size = N;
        quickFindUF = new WeightedQuickUnionUF(N*N+2);
        head = coordinateToNum(new int[] {0, 0});
        tail = coordinateToNum(new int[] {size+1, size+1});
    }

    private int coordinateToNum(int[] loc)  {
        if (loc[0] == 0)  {
            return 0;
        } else if (loc[0] == size+1) {
            return size*size+1;
        } else if (loc[0] <= size && loc[1] > 0 && loc[1] <= size) {
            return (loc[0]-1)*size + loc[1];
        }
        throw new IllegalArgumentException("location is "+ loc[0] + " " +loc[1]);
    }

    private boolean checkRange(int i, int j) {
        if (i > size || i < 1 || j > size || j < 1) {
            throw new IndexOutOfBoundsException("i is "+i + " j is "+ j);
        }
        return true;
    }

    private int[][] around(int i, int j) {
        int[] left =  {i, j-1};
        int[] right =  {i, j+1};
        int[] up =  {i-1, j};
        int[] down =  {i+1, j};

        int[] headSite =  {0, 0};
        int[] tailSite =  {size+1, size+1};
        if (i == 1) {
            if (j == 1) {
                return new int[][] {right, down, headSite};
            } else if (j > 1 && j < size) {
                return new int[][] {left, right, down, headSite};
            } else if (j == size) {
                return new int[][] {left, down, headSite};
            }
        } else if (i > 1 && i < size) {
            if (j == 1) {
                return new int[][] {up, right, down};
            } else if (j > 1 && j < size) {
                return new int[][] {up, right, down, left};
            } else if (j == size) {
                return new int[][] {up, left, down};
            }
        } else if (i == size) {
            if (j == 1)  {
                return new int[][] {right, up, tailSite};
            } else if (j > 1 && j < size)  {
                return new int[][] {up, left, right, tailSite};
            } else if (j == size) {
                return new int[][] {left, up, tailSite};
            }
        }
        throw new IllegalArgumentException("i is " + i + " j is " + j);
    }

    public void open(int i, int j)  {          // open site (row i, column j) if it is not open already
        if (isOpen(i, j)) {
            return;
        }
        openSites++;

        checkRange(i, j);
        sites[i][j] = true;

        int now = coordinateToNum(new int[] {i, j});
        int[][] around = around(i, j);
        for (int[] site : around)  {
            if (site[0] == 0 || site[0] == size+1 || isOpen(site[0], site[1])) {
                int num = coordinateToNum(site);
                quickFindUF.union(num, now);
            }
        }
    }

    public boolean isOpen(int i, int j)  {     // is site (row i, column j) open?
        checkRange(i, j);
        return sites[i][j];
    }

    public boolean isFull(int i, int j)  {     // is site (row i, column j) full?
        checkRange(i, j);
        int now = coordinateToNum(new int[]{i, j});
        return quickFindUF.connected(head, now);
    }


    public boolean percolates()  {             // does the system percolate?
        return quickFindUF.connected(head, tail);
    }


}