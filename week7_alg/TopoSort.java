
//TODO: consider checking for loops in graph
public class TopoSort {
    private boolean[] marked;
    private int[] result;
    private int resIndex;
    public int[] sort(Graph g) {
        marked = new boolean[g.V()];
        result = new int[g.V()];
        resIndex = 0;

        for(int i = 0; i < g.V(); i++) {
            if(!marked[i]) {
                dfs(g, i);
            }
        }

        return result;
    }

    private void dfs(Graph g, int v) {
        for(int i : g.adj(v)) {
            if(!marked[i]) {
                dfs(g, i);
            }
        }
        result[resIndex++] = v;
    }
}
