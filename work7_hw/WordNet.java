
public class WordNet {
    private Bag<String>[] synsets;
    private int synsetCount = 0;
    private Digraph hypernyms;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        Bag<String> lines = readAllLines(synsets);
        this.synsets = (Bag<String>[])new Bag[lines.size()];
        this.hypernyms = new Digraph(lines.size());

        for (String line : lines) {
            String[] vals = line.split(",");

            Bag<String> synset = new Bag<String>();
            for(String s : vals[1].split(" ")) {
                synset.add(s);
            }
            this.synsets[synsetCount++] = synset;
        }

        lines = readAllLines(hypernyms);
        for (String line : lines) {
            String[] vals = line.split(",");
            int synsetId = Integer.parseInt(vals[0]);
            for(int j = 1; j < vals.length; j++) {
                this.hypernyms.addEdge(Integer.parseInt(vals[j]), synsetId);
            }
        }

        DirectedCycle dc = new DirectedCycle(this.hypernyms);
        if(dc.hasCycle()) {
            throw new java.lang.IllegalArgumentException();
        }
    }

    private Bag<String> readAllLines(String inputFile) {
        Bag<String> lines = new Bag<String>();
        In in = new In(inputFile);
        while(in.hasNextLine()) {
            lines.add(in.readLine());
        }
        in.close();
        return lines;
    }

    // the set of nouns (no duplicates), returned as an Iterable
    public Iterable<String> nouns() {
        Bag<String> bag = new Bag<String>();

        for(int i = 0; i < synsets.length; i++) {
            for(String s : synsets[i]) {
                bag.add(s);
            }
        }

        return bag;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return findWordIndex(word) != -1;
    }

    private int findWordIndex(String word) {
        int startIndex = 0, endIndex = synsets.length - 1;
        while(endIndex > startIndex) {
            int i = 0;
            int index = startIndex + (endIndex - startIndex) / 2;
            for(String s : synsets[index]) {
                int res = word.compareTo(s);
                if(res == 0) {
                    return index;
                } else if(res < 0) {
                    if(i == 0) {
                        endIndex = index - 1;
                        break;
                    } else{
                        return -1;
                    }
                } else if(i + 1 == synsets[index].size()) {
                    startIndex = index + 1;
                }
                i++;
            }
        }

        for(String s : synsets[startIndex]) {
            if(word.compareTo(s) == 0) {
                return startIndex;
            }
        }
        return -1;
    }

    public int distance(String nounA, String nounB) {
        int nounAIndex = findWordIndex(nounA),
            nounBIndex = findWordIndex(nounB);
        if(nounAIndex == -1 || nounBIndex == -1) {
            throw new java.lang.IllegalArgumentException();
        }

        BreadthFirstDirectedPaths dp1 = new BreadthFirstDirectedPaths(this.hypernyms, nounAIndex);
        BreadthFirstDirectedPaths dp2 = new BreadthFirstDirectedPaths(this.hypernyms, nounBIndex);

        int minPath = Integer.MAX_VALUE;
        for(int i = 0; i < dp1.marked().length; i++) {
            if(dp1.marked()[i] && dp2.marked()[i]) {
                int path = dp1.distTo(i) + dp2.distTo(i);
                if(path < minPath) {
                    minPath = path;
                }
            }
        }

        return minPath;
    }



    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        return null;
    }

    public static void main(String[] args) {
        WordNet wordnet = new WordNet(args[0], args[1]);
//        Outcast outcast = new Outcast(wordnet);
//        for (int t = 2; t < args.length; t++) {
//            String[] nouns = In.readStrings(args[t]);
//            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
//        }
    }


    private class DirectedCycle {
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

    private class BreadthFirstDirectedPaths {
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
            marked[source] = true;
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

        public boolean[] marked() {
            return marked;
        }
    }
}
