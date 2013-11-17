

public class DirectedCycle {
    private Digraph graph;
    private boolean[] onStack;
    private int[] edgeTo;
    private boolean[] marked;
    private boolean hasCycle = false;
    private Queue<Integer> cycle = new Queue<Integer>();

    public DirectedCycle(Digraph graph) {
        this.graph = graph;
        this.marked = new boolean[graph.V()];

        for(int i = 0; i < graph.V(); i++) {
            if(!marked[i]) {
                marked[i] = true;

                edgeTo = new int[graph.V()];
                onStack = new boolean[graph.V()];
                if(!dfs(graph, i)) {
                    hasCycle = true;
                    break;
                }
            }
        }
    }

    private boolean dfs(Digraph g, int v) {
        onStack[v] = true;
        edgeTo[v] = v;
        for(int i : g.adj(v)) {
            if(!marked[i]) {
                marked[i] = true;
                edgeTo[v] = i;
                if(!dfs(graph, i)) {
                    return false;
                }
            } else if(onStack[i]) {
                int index = i;

                while(edgeTo[index] != index) {
                    cycle.enqueue(index);
                    index = edgeTo[index];
                }
                cycle.enqueue(index);
                return false;
            }
        }
        onStack[v] = false;
        return true;
    }

    //does G have a directed cycle?
    public boolean hasCycle() {
        return this.hasCycle;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
