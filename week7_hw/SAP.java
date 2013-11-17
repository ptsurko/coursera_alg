
public class SAP {
    private Digraph graph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph graph) {
        this.graph = new Digraph(graph);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if(v < 0 || v >= graph.V() || w < 0 || w >= graph.V()) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        BreadthFirstDirectedPaths dp1 = new BreadthFirstDirectedPaths(this.graph, v);
        BreadthFirstDirectedPaths dp2 = new BreadthFirstDirectedPaths(this.graph, w);

        int minPath = Integer.MAX_VALUE;
        boolean found = false;
        for(int i = 0; i < dp1.marked().length; i++) {
            if(dp1.marked()[i] && dp2.marked()[i]) {
                int path = dp1.distTo(i) + dp2.distTo(i);
                if(path < minPath) {
                    minPath = path;
                    found = true;
                }
            }
        }

        return found ? minPath : -1;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if(v < 0 || v >= graph.V() || w < 0 || w >= graph.V()) {
            throw new java.lang.IndexOutOfBoundsException();
        }

        BreadthFirstDirectedPaths dp1 = new BreadthFirstDirectedPaths(this.graph, v);
        BreadthFirstDirectedPaths dp2 = new BreadthFirstDirectedPaths(this.graph, w);

        int minPath = Integer.MAX_VALUE, minIndex = -1;
        boolean found = false;
        for(int i = 0; i < dp1.marked().length; i++) {
            if(dp1.marked()[i] && dp2.marked()[i]) {
                int path = dp1.distTo(i) + dp2.distTo(i);
                if(path < minPath) {
                    minPath = path;
                    minIndex = i;
                    found = true;
                }
            }
        }

        return found ? minIndex : -1;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        for(int i : v) {
            if(i < 0 || i >= graph.V()) {
                throw new java.lang.IndexOutOfBoundsException();
            }
        }
        for(int i : w) {
            if(i < 0 || i >= graph.V()) {
                throw new java.lang.IndexOutOfBoundsException();
            }
        }

        BreadthFirstDirectedPaths dp1 = new BreadthFirstDirectedPaths(this.graph, v);
        BreadthFirstDirectedPaths dp2 = new BreadthFirstDirectedPaths(this.graph, w);


        int minPath = Integer.MAX_VALUE;
        boolean found = false;
        for(int i = 0; i < dp1.marked().length; i++) {
            if(dp1.marked()[i] && dp2.marked()[i]) {
                int path = dp1.distTo(i) + dp2.distTo(i);
                if(path < minPath) {
                    minPath = path;
                    found = true;
                }
            }
        }

        return found ? minPath : -1;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        for(int i : v) {
            if(i < 0 || i >= graph.V()) {
                throw new java.lang.IndexOutOfBoundsException();
            }
        }
        for(int i : w) {
            if(i < 0 || i >= graph.V()) {
                throw new java.lang.IndexOutOfBoundsException();
            }
        }

        BreadthFirstDirectedPaths dp1 = new BreadthFirstDirectedPaths(this.graph, v);
        BreadthFirstDirectedPaths dp2 = new BreadthFirstDirectedPaths(this.graph, w);

        int minPath = Integer.MAX_VALUE, minIndex = -1;
        boolean found = false;
        for(int i = 0; i < dp1.marked().length; i++) {
            if(dp1.marked()[i] && dp2.marked()[i]) {
                int path = dp1.distTo(i) + dp2.distTo(i);
                if(path < minPath) {
                    minPath = path;
                    minIndex = i;
                    found = true;
                }
            }
        }

        return found ? minIndex : -1;
    }

    // for unit testing of this class (such as the one below)
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }

    private class BreadthFirstDirectedPaths {
        private Digraph graph;

        private int[] distTo;
        private boolean[] marked;

        public BreadthFirstDirectedPaths(Digraph graph, int source) {
            this.graph = graph;

            this.distTo = new int[graph.V()];
            for(int i = 0; i < distTo.length; i++) {
                distTo[i] = -1;
            }
            this.marked = new boolean[graph.V()];

            bfs(this.graph, source);
        }

        public BreadthFirstDirectedPaths(Digraph graph, Iterable<Integer> sources) {
            this.graph = graph;

            this.distTo = new int[graph.V()];
            for(int i = 0; i < distTo.length; i++) {
                distTo[i] = -1;
            }
            this.marked = new boolean[graph.V()];

            bfs(this.graph, sources);
        }

        private void bfs(Digraph g, int source) {
            int[] stack = new int[g.V()];
            int startIndex = 0, endIndex = 0;
            stack[endIndex++] = source;
            marked[source] = true;
            distTo[source] = 0;
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

        private void bfs(Digraph g, Iterable<Integer> sources) {
            int[] stack = new int[g.V()];
            int startIndex = 0, endIndex = 0;
            for(Integer source : sources) {
                stack[endIndex++] = source;
                marked[source] = true;
                distTo[source] = 0;
            }
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
            return distTo[v] >= 0;
        }

        public boolean[] marked() {
            return marked;
        }
    }
}
