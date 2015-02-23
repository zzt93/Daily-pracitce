/**
 * Created by zzt on 2/1/15.
 */
public class PercolationStats_h {
    private Percolation percolation;
    private int trial;
    private int[] x;
    private double mean;
    private double stddev;
    private int size;


    public PercolationStats_h(int N, int T)  {
        if (N <= 0 || T <= 0)  {
            throw new IllegalArgumentException("N is "+N + "T is " + T);
        }
        size = N;

        trial = T;
        x = new int[trial];

        for (int i = 0; i < trial; i++)  {
            x[i] = simulate();
        }
    }

    public double mean() {
        mean = StdStats.mean(x);
        return mean/(size*size);
    }

    private int simulate() {
        int openSites = 0;
        percolation = new Percolation(size);
        while (!percolation.percolates()) {
            int i = StdRandom.uniform(1, size+1);
            int j = StdRandom.uniform(1, size+1);
            if (percolation.isOpen(i, j)) {
                continue;
            }
            openSites++;
            percolation.open(i, j);
        }
        return openSites;
    }

    /**
     * @see PercolationStats#mean()
     * @return standard deviation
     */
    public double stddev() {
        stddev = StdStats.stddev(x);
        return stddev/(size*size);
    }

    public double confidenceLo() {
        return mean-1.96*Math.sqrt(stddev/trial);
    }
    public double confidenceHi() {
        return mean+1.96*Math.sqrt(stddev/trial);
    }

    public static void main(String[] args)  {
        if (args.length == 2) {
            PercolationStats_h percolationStats = new PercolationStats_h(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            int N = percolationStats.size;

            System.out.println("mean                    = "+percolationStats.mean()/(N*N));
            System.out.println("stddev                  = " + percolationStats.stddev()/(N*N));
            System.out.println("95% confidence interval = "+percolationStats.confidenceHi()/(N*N)+ ", "+percolationStats.confidenceLo()/(N*N));
        } else  {
            System.out.println("Usage: java PercolationStats N T");
        }
    }
}