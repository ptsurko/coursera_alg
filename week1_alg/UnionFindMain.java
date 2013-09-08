
public class UnionFindMain {
    public static void main(String[] args) {
        int N = 10;
        IUnionFind unionFind = new CompressedWeightedQuickUnion(N);
        int[][] list = new int[][] {
                {4, 3},
                {3, 8},
                {6, 5},
                {9, 4},
                {2, 1},
                {5, 0},
                {7, 2},
                {6, 1}};
        int index = 0;
        while(index < list.length) {
            int p = list[index][0], q = list[index][1];
            if(!unionFind.connected(p, q)) {
                unionFind.union(p, q);
            }
            StdOut.println(p + " " + q);
            index++;
        }
        StdOut.println(unionFind.count() + " components");
    }
}
