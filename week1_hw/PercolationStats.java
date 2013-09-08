public class PercolationStats {
    private int N, T;
    private double[] x;
    public PercolationStats(int N, int T) throws IllegalArgumentException    // perform T independent computational experiments on an N-by-N grid
    {
        if(N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        this.N = N;
        this.T = T;

        x = new double[T];

        for(int k = 0; k < T; k++) {
            Percolation percolation = new Percolation(N);
            int counter = 0;
            while(true) {
                int i = StdRandom.uniform(N) + 1;
                int j = StdRandom.uniform(N) + 1;

                if(!percolation.isOpen(i,j)) {
                    counter++;
                    percolation.open(i,j);
                    if(percolation.percolates()) {
                        break;
                    }
                }
            }
            x[k] = ((double)counter) / (N * N);
        }
    }

    public double mean()                     // sample mean of percolation threshold
    {
        double sum = 0;
        for(int i = 0; i < x.length; i++) {
            sum += x[i];
        }
        return sum / T;
    }
    public double stddev()                   // sample standard deviation of percolation threshold
    {
        double mean = mean(), sum = 0;

        for(int i = 0; i < x.length; i++) {
            sum += (x[i] - mean) * (x[i] - mean);
        }
        return Math.sqrt(sum / (T - 1));
    }
    public double confidenceLo()             // returns lower bound of the 95% confidence interval
    {
        return mean() - (1.96 * stddev()) / Math.sqrt(T);

    }
    public double confidenceHi()             // returns upper bound of the 95% confidence interval
    {
        return mean() + (1.96 * stddev()) / Math.sqrt(T);
    }
    public static void main(String[] args) throws IllegalAccessException  // test client, described below
    {
        int N = 123;// Integer.parseInt(args[0]);
        int T = 1;//Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(N, T);
        double mean = percolationStats.mean();
        double stddev = percolationStats.stddev();
        double confidenceLo = percolationStats.confidenceLo();
        double confidenceHi = percolationStats.confidenceHi();
    }
}
