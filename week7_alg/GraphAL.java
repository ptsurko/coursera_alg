/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 11/16/13
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class GraphAL extends Graph {

    public Bag<Integer>[] adj;
    public int e = 0;
    public GraphAL(int v) {
        super(v);
        adj = (Bag<Integer>[])new Bag[v];
    }

    @Override
    public int E() {
        return this.e;
    }

    @Override
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        this.e++;
    }

    @Override
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public String toString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
