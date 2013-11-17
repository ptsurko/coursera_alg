
public class DigraphAL extends Graph {
    private Bag<Integer>[] adj;
    private int e;
    public DigraphAL(int v) {
        super(v);

        adj = (Bag<Integer>[])new Bag[v];
    }
    @Override
    public int E() {
        return e;
    }

    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
        e++;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public String toString() {
        return null;
    }
}
