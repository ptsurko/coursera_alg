
public class BreadthFirstDirectedPaths {
    private Digraph graph;
    private int source;

    private int[] distTo;
    private boolean[] marked;

    public BreadthFirstDirectedPaths(Digraph graph, int s) {
        this.graph = graph;
        this.source = s;

        this.distTo = new int[graph.V()];
        this.marked = new boolean[graph.V()];

        bfs(this.graph, this.source);
    }

    private void bfs(Digraph g, int source) {
        int[] stack = new int[g.V()];
        int startIndex = 0, endIndex = 0;
        stack[endIndex++] = source;

        while(startIndex != endIndex) {
            int v = stack[startIndex];
            for(int i : g.adj(v)) {
                if(!marked[i]) {
                    marked[i] = true;
                    distTo[i] = distTo[v] + 1;

                    stack[endIndex++] = i;
                }
            }

            startIndex++;
        }
    }

    public int distTo(int v){
        return distTo[v];
    }

    // is there a directed path from s (or sources) to v?
    public boolean hasPathTo(int v) {
        return v != source && distTo[v] > 0;
    }

    // shortest path from s (or sources) to v; null if no such path
    public Iterable<Integer> pathTo(int v) {
        return null;
    }
}
