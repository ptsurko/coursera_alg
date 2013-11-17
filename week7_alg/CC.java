public class CC {
    public boolean[] marked;
    public int[] id;
    public int count = 0;
    public CC(Graph g) {
        marked = new boolean[g.V()];

        for(int v = 0; v < g.V(); v++) {
            if(!marked[v]) {
                dfs(g, v);
                count++;
            }
        }
    }

    public void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = count;
        for(int w : g.adj(v)) {
            if(!marked[w]) {
                dfs(g, w);
            }
        }
    }

    public boolean connected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }
}
