
/**
 * Created with IntelliJ IDEA.
 * User: pasha
 * Date: 9/1/13
 * Time: 8:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class CompressedWeightedQuickUnion implements IUnionFind {
    private int[] id;
    private int[] weights;
    private int count;
    public CompressedWeightedQuickUnion(int N) {
        count = N;
        id = new int[N];
        weights = new int[N];

        for(int i = 0; i < N; i++) {
            id[i] = i;
            weights[i] = 1;
        }
    }

    public void union(int p, int q) {
        int pGroup = find(p), qGroup = find(q);

        if(pGroup != qGroup){
            if(weights[pGroup] > weights[qGroup]) {
                while(id[q] != q) {
                    q = id[q];
                    id[q] = pGroup;
                }
                id[qGroup] = pGroup;
                weights[pGroup] += weights[qGroup];
            } else {
                while(id[p] != p) {
                    p = id[p];
                    id[p] = qGroup;
                }
                id[pGroup] = qGroup;
                weights[qGroup] += weights[pGroup];
            }

            count--;
        }
    }

    public int find(int p) {
        while(id[p] != p) {
            p = id[p];
        }
        return id[p];
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }
}
