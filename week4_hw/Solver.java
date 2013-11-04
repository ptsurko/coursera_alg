import java.util.Comparator;
import java.util.Iterator;

public class Solver {
    private SearchNode solution;
    private boolean solvable = false;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {

        MinPQ<SearchNode> minPQ1 = new MinPQ<SearchNode>(initial.dimension(), new SearchNodeComparator());
        minPQ1.insert(new SearchNode(initial, 0, null));

        MinPQ<SearchNode> minPQ2 = new MinPQ<SearchNode>(initial.dimension(), new SearchNodeComparator());
        minPQ2.insert(new SearchNode(initial.twin(), 0, null));

        while(true) {
            if(minPQ1.isEmpty() || minPQ2.isEmpty()) {
                break;
            } else {
                SearchNode currentSearchNode1 = minPQ1.delMin();
                SearchNode currentSearchNode2 = minPQ2.delMin();

                if(currentSearchNode1.board.isGoal()) {
                    this.solution = currentSearchNode1;
                    this.solvable = true;
                    break;
                } if (currentSearchNode2.board.isGoal()){
                    break;
                } else {
                    for(Board neighbor:currentSearchNode1.board.neighbors()) {
                        if(currentSearchNode1.prevSearchNode == null || !currentSearchNode1.prevSearchNode.board.equals(neighbor)) {
                            minPQ1.insert(new SearchNode(neighbor, currentSearchNode1.movesMade + 1, currentSearchNode1));
                        }
                    }

                    for(Board neighbor:currentSearchNode2.board.neighbors()) {
                        if(currentSearchNode2.prevSearchNode == null || !currentSearchNode2.prevSearchNode.board.equals(neighbor)) {
                            minPQ2.insert(new SearchNode(neighbor, currentSearchNode2.movesMade + 1, currentSearchNode2));
                        }
                    }
                }
            }
        }
    }

    private class SearchNodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode a, SearchNode b) {
            return (a.movesMade + a.board.manhattan()) - (b.movesMade + b.board.manhattan());
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solvable;
    }
    // min number of moves to solve initial board; -1 if no solution
    public int moves() {
        return solvable ? this.solution.movesMade : -1;
    }
    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution() {
        if(solvable) {
            return new SolutionIterable(this.solution);
        }

        return null;
    }

    private class SolutionIterable implements Iterable<Board> {
        private SearchNode node;
        public SolutionIterable(SearchNode node){
            this.node = node;
        }
        @Override
        public Iterator<Board> iterator() {
            return new SolutionIterator(this.node);
        }

        private class SolutionIterator implements Iterator<Board> {
            private Board[] result;
            private int index = 0;
            public SolutionIterator(SearchNode node){
                SearchNode current = node;
                result = new Board[node.movesMade + 1];
                for(int i = result.length - 1; i >= 0; i--) {
                    result[i] = current.board;
                    current = current.prevSearchNode;
                }
            }

            @Override
            public boolean hasNext() {
                return index < result.length;
            }

            @Override
            public Board next() {
                return result[index++];
            }

            @Override
            public void remove() { }
        }
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    private class SearchNode {
        public SearchNode(Board board, int movesMade, SearchNode prevSearchNode) {
            this.board = board;
            this.movesMade = movesMade;
            this.prevSearchNode = prevSearchNode;
        }

        private boolean wasBoardAlreadyVisited(Board board) {
            SearchNode current = this;
            while(current != null) {
                if(current.board.equals(board)) {
                    return true;
                }

                current = current.prevSearchNode;
            }

            return false;
        }

        public Board board;
        public int movesMade;
        public SearchNode prevSearchNode;
    }
}