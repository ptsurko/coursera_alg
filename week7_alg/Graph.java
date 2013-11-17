/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 11/16/13
 * Time: 11:29 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Graph {
    protected int v = 0;
    public Graph(int v) {
        this.v = v;
    }

    //number of vertices
    public int V() { return this.v; }

    //number of edges
    public abstract int E();

    public abstract void addEdge(int v, int w);

    public abstract Iterable<Integer> adj(int v);

    public abstract String toString();
}
