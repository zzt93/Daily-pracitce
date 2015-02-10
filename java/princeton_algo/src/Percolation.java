import java.util.Arrays;

/**
 * Created by zzt on 2/1/15.
 */
public class Percolation {
    private WeightedQuickUnionUF quickFindUF;
    private final int head;
    private final int tail;
    private final int size;
    private boolean[][] sites;
    private int openSites = 0;

    public Percolation(int N) throws IllegalArgumentException {               // create N-by-N grid, with all sites blocked
        if (N <= 0){
            throw new IllegalArgumentException("N is "+N);
        }
        sites = new boolean[N+1][N+1];
        size = N;
        quickFindUF = new WeightedQuickUnionUF(N*N+2);
        head = coordinateToNum(new int[]{0, 0});
        tail = coordinateToNum(new int[]{size+1, size+1});
    }

    private int coordinateToNum(int[] loc) {
        if (loc[0] == 0) {
            return 0;
        } else if (loc[0] == size+1){
            return size*size+1;
        } else if (loc[0] <= size && loc[1] > 0 && loc[1] <= size){
            return (loc[0]-1)*size + loc[1];
        }
        throw new IllegalArgumentException("location is "+ Arrays.toString(loc));
    }

    private boolean checkRange(int i, int j){
        if (i > size || i < 1){
            throw new IndexOutOfBoundsException("i is "+i + " j is "+ j);
        }
        return true;
    }

    private int[][] around(int i, int j){
        int[] left = {i, j-1};
        int[] right = {i, j+1};
        int[] up = {i-1, j};
        int[] down = {i+1, j};

        int[] head = {0, 0};
        int[] tail = {size+1, size+1};
        if (i == 1){
            if (j == 1){
                return new int[][]{right, down, head};
            } else if (j > 1 && j < size){
                return new int[][]{left, right, down, head};
            } else if (j == size){
                return new int[][]{left, down, head};
            }
        } else if (i > 1 && i < size){
            if (j == 1){
                return new int[][]{up, right, down};
            } else if (j > 1 && j < size){
                return new int[][]{up, right, down, left};
            } else if (j == size){
                return new int[][]{up, left, down};
            }
        } else if (i == size){
            if (j == 1) {
                return new int[][]{right, up, tail};
            } else if (j > 1 && j < size) {
                return new int[][]{up, left, right, tail};
            } else if (j == size){
                return new int[][]{left, up, tail};
            }
        }
        throw new IllegalArgumentException("i is " + i + " j is " + j);
    }

    public void open(int i, int j) {          // open site (row i, column j) if it is not open already
        if (isOpen(i, j)){
            return;
        }
        openSites++;

        checkRange(i, j);
        sites[i][j] = true;

        int now = coordinateToNum(new int[]{i,j});
        int[][] around = around(i,j);
        for (int[] site : around) {
            if (isOpen(site[0], site[1])){
                int num = coordinateToNum(site);
                quickFindUF.union(num, now);
            }
        }
    }

    public boolean isOpen(int i, int j) {     // is site (row i, column j) open?
        if (i == 0 || i == size+1){
            return true;
        }
        checkRange(i, j);
        return sites[i][j];
    }

    public boolean isFull(int i, int j) {     // is site (row i, column j) full?
        checkRange(i, j);
        return !isOpen(i, j);
    }



    public boolean percolates() {             // does the system percolate?
        return quickFindUF.connected(head, tail);
    }

    public double simulate(){
        while (!percolates()){
            int i = StdRandom.uniform(1, size+1);
            int j = StdRandom.uniform(1, size+1);
            if (isOpen(i, j)){
                continue;
            }
            open(i, j);
        }
        return openSites*1.0/(size*size);
    }


    public static void main(String[] args) {
        Percolation percolation = new Percolation(200);
        System.out.println(percolation.simulate());
    }
}
