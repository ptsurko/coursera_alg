
public class WordNet {
    private Digraph hypernyms;
    private ST<String, Bag<Integer>> synsets;
    private String[] synsets2;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        Bag<String> lines = readAllLines(synsets);
        this.synsets = new ST<String, Bag<Integer>>();

        this.hypernyms = new Digraph(lines.size());
        this.synsets2 = new String[lines.size()];

        for (String line : lines) {
            String[] vals = line.split(",");
            Integer index = Integer.parseInt(vals[0]);

            for(String s : vals[1].split(" ")) {
                if(!this.synsets.contains(s)) {
                    this.synsets.put(s, new Bag<Integer>());
                }
                this.synsets.get(s).add(index);
            }

            synsets2[index] = vals[1];
        }

        lines = readAllLines(hypernyms);

        for (String line : lines) {
            String[] vals = line.split(",");
            int synsetId = Integer.parseInt(vals[0]);
            for(int j = 1; j < vals.length; j++) {
                this.hypernyms.addEdge(synsetId, Integer.parseInt(vals[j]));
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
        return lines;
    }

    // the set of nouns (no duplicates), returned as an Iterable
    public Iterable<String> nouns() {
        return this.synsets;
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return this.synsets.contains(word);
    }

    public int distance(String nounA, String nounB) {
        if(!this.synsets.contains(nounA) || !this.synsets.contains(nounB)) {
            throw new java.lang.IllegalArgumentException();
        }

        SAP sap = new SAP(this.hypernyms);
        return sap.length(this.synsets.get(nounA), this.synsets.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {

        if(!this.synsets.contains(nounA) || !this.synsets.contains(nounB)) {
            throw new java.lang.IllegalArgumentException();
        }

        SAP sap = new SAP(this.hypernyms);
        int ancestor = sap.ancestor(this.synsets.get(nounA), this.synsets.get(nounB));

        return this.synsets2[ancestor];
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
}
