public class Percolation {
    private int N;
    private int leftIndex, rightIndex, topIndex, bottomIndex;
    private boolean[] sites;
    private byte[] isFull;
    private WeightedQuickUnionUF quickUnionUF;
    private boolean percolates = false;
    public Percolation(int N) { // create N-by-N grid, with all sites blocked
        this.N = N;

        sites = new boolean[N * N];
        isFull = new byte[N * N];
        quickUnionUF = new  WeightedQuickUnionUF(N * N);

    }

    public void open(int p, int q) { // open site (row i, column j) if it is not already
        int i = p - 1;
        int j = q - 1;
        if (i >= N || i < 0 || j >= N || j < 0) {
            throw new IndexOutOfBoundsException();
        }

        int currentIndex = j + i * N;
        sites[currentIndex] = true;
        if (i == 0) {
            isFull[currentIndex] = 1;
        } else if (i == N - 1) {
            isFull[currentIndex] = -1;
        }

        leftIndex = j - 1 + i * N;
        rightIndex = j + 1 + i * N;
        topIndex = j + (i - 1) * N;
        bottomIndex = j + (i + 1) * N;

        if (i - 1 >= 0 && sites[topIndex]) {
            connectWith(currentIndex, topIndex);
        }

        if ((i + 1) < N && sites[bottomIndex]) {
            connectWith(currentIndex, bottomIndex);
        }

        if (j - 1 >= 0 && sites[leftIndex]) {
            connectWith(currentIndex, leftIndex);
        }

        if ((j + 1) < N && sites[rightIndex]) {
            connectWith(currentIndex, rightIndex);
        }
    }

    private void connectWith(int currentIndex, int connectWithIndex) {
        int tempRoot = quickUnionUF.find(connectWithIndex);
        int tempCurrentRoot = quickUnionUF.find(currentIndex);
        quickUnionUF.union(currentIndex, connectWithIndex);
        byte fullVal = (byte) (isFull[tempCurrentRoot] + isFull[tempRoot]);
        if (isFull[tempCurrentRoot] != isFull[tempRoot]) {
            percolates = percolates | (fullVal == 0);
            if (fullVal == 0) {
                isFull[tempCurrentRoot] = 1;
                isFull[tempRoot] = 1;
            } else {
                isFull[tempCurrentRoot] = fullVal;
                isFull[tempRoot] = fullVal;
            }
        }
    }

    public boolean isOpen(int p, int q) { // is site (row i, column j) open?
        int i = p - 1;
        int j = q - 1;
        if (i >= N || i < 0 || j >= N || j < 0) {
            throw new IndexOutOfBoundsException();
        }

        return sites[j + i * N];
    }

    public boolean isFull(int p, int q) {  // is site (row i, column j) full?
        int i = p - 1;
        int j = q - 1;
        if (i >= N || i < 0 || j >= N || j < 0) {
            throw new IndexOutOfBoundsException();
        }

        return sites[j + i * N] && isFull[quickUnionUF.find(j + i * N)] == 1;
    }

    public boolean percolates() { // does the system percolate?
        if (N == 1) {
            return isFull(1, 1);
        } else {
            return percolates;
        }
    }
}



