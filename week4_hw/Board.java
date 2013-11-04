import java.util.Iterator;

public class Board {
    private int[][] blocks;
    private int hamming = 0;
    private int manhattan = 0;
    private boolean isGoal = true;

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        this.blocks = blocks;

        int block;
        for(int i = 0; i < this.blocks.length; i++) {
            for(int j = 0; j < this.blocks.length; j++) {
                block = this.blocks[i][j];
                if(block != 0 && block != (i * this.blocks.length + (j + 1))) {
                    manhattan += Math.abs((block - 1) / this.blocks.length - i) + Math.abs((block - 1) % this.blocks.length - j);
                    hamming++;
                    isGoal = false;
                }
            }
        }
    }

    // board dimension N
    public int dimension() {
        return this.blocks.length;
    }

    // number of blocks out of place
    public int hamming() {
        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        return manhattan;
    }
    // is this board the goal board?
    public boolean isGoal() {
        return isGoal;
    }

    // a board obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
        int[][] result = new int[blocks.length][blocks.length];
        boolean swapped = false;
        for(int i = 0; i < this.blocks.length; i++) {
            for(int j = 0; j < this.blocks.length; j++) {
                result[i][j] = this.blocks[i][j];
                if(j > 0 && result[i][j] != 0 && result[i][j - 1] != 0 && !swapped) {
                    int temp = result[i][j];
                    result[i][j] = result[i][j - 1];
                    result[i][j - 1] = temp;
                    swapped = true;
                }
            }
        }

        return new Board(result);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board b = (Board)y;
        if(this.manhattan != b.manhattan || this.hamming != b.hamming) {
            return false;
        }
        for(int i = 0; i < this.blocks.length; i++) {
            for(int j = 0; j < this.blocks.length; j++) {
                if (this.blocks[i][j] != b.blocks[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        return new NeighborsIterable(this);
    }

    private class NeighborsIterable implements Iterable<Board> {
        private Board board;
        public NeighborsIterable(Board board) {
            this.board = board;
        }
        @Override
        public Iterator<Board> iterator() {
            return new NeighborsIterator(this.board);
        }
    }

    private class NeighborsIterator implements Iterator<Board> {
        private Board board;

        private Board[] neighbors = new Board[4];
        private int boardCount = 0;
        private int boardIndex = 0;
        public NeighborsIterator(Board board) {
            this.board = board;

            Index zero = getZeroIndex();

            if (zero.i > 0) {
                int[][] topMove = copyBoard(board.blocks);
                swap(topMove, zero.i, zero.j, zero.i - 1, zero.j);
                neighbors[boardCount++] = new Board(topMove);
            }
            if (zero.i < board.dimension() - 1) {
                int[][] bottomMove = copyBoard(board.blocks);
                swap(bottomMove, zero.i, zero.j, zero.i + 1, zero.j);
                neighbors[boardCount++] = new Board(bottomMove);
            }
            if (zero.j > 0) {
                int[][] leftMove = copyBoard(board.blocks);
                swap(leftMove, zero.i, zero.j, zero.i, zero.j - 1);
                neighbors[boardCount++] = new Board(leftMove);
            }
            if (zero.j < board.dimension() - 1) {
                int[][] rightMove = copyBoard(board.blocks);
                swap(rightMove, zero.i, zero.j, zero.i, zero.j + 1);
                neighbors[boardCount++] = new Board(rightMove);
            }
        }

        @Override
        public boolean hasNext() {
            return boardIndex < boardCount;
        }

        @Override
        public Board next() {
            return neighbors[boardIndex++];
        }

        @Override
        public void remove() { }

        private int[][] copyBoard(int[][] blocks) {
            int[][] result = new int[blocks.length][blocks.length];
            for(int i = 0; i < this.board.blocks.length; i++) {
                for(int j = 0; j < this.board.blocks.length; j++) {
                    result[i][j] = this.board.blocks[i][j];
                }
            }
            return result;
        }

        private void swap(int[][] blocks, int i1, int j1, int i2, int j2) {
            int temp = blocks[i1][j1];
            blocks[i1][j1] = blocks[i2][j2];
            blocks[i2][j2] = temp;
        }

        private Index getZeroIndex() {
            for(int i = 0; i < this.board.blocks.length; i++) {
                for(int j = 0; j < this.board.blocks.length; j++) {
                    if (this.board.blocks[i][j] == 0) {
                        return new Index(i, j);
                    }
                }
            }

            //TODO: should throw exception
            return new Index(0, 0);
        }

        private class Index {
            public Index(int i, int j) {
                this.i = i;
                this.j = j;
            }

            public int i;
            public int j;
        }
    }

    // string representation of the board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(blocks.length + "\n");
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}