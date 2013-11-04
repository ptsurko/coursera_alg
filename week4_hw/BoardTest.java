import org.junit.Test;

public class BoardTest {
    @Test
    public void testHaming() {
        int[][] b =  {{8,1,3},{4,0,2},{7,6,5}};

        Board board = new Board(b);
        assert board.hamming() == 5;
    }

    @Test
    public void testManhattan() {
        int[][] b =  {{8,1,3},{4,0,2},{7,6,5}};

        Board board = new Board(b);
        assert board.manhattan() == 10;
    }

    @Test
    public void testManhattan2() {
        int[][] b =  {{4,2,3,8},{5,6,1,0},{9,14,7,12},{13,15,11,10}};

        Board board = new Board(b);
        assert board.manhattan() == 14;
    }

    @Test
    public void testManhattan3() {
        int[][] b =  {{1,2,3},{4,0,5},{7,8,6}};

        Board board = new Board(b);
        assert board.manhattan() == 2;
    }

    @Test
    public void testManhattan4() {
        int[][] b =  {{8,1,3},{4,0,2},{7,6,5}};

        Board board = new Board(b);
        assert board.manhattan() == 10;
    }

    @Test
    public void testIsGoalFalse() {
        int[][] b =  {{8,1,3},{4,0,2},{7,6,5}};

        Board board = new Board(b);
        assert board.isGoal() == false;
    }

    @Test
    public void testIsGoalTrue() {
        int[][] b =  {{1,2,3},{4,5,6},{7,8,0}};

        Board board = new Board(b);
        assert board.isGoal() == true;
    }

    @Test
    public void testisEqualFalse() {
        int[][] b1 =  {{1,2,3},{4,5,6},{7,8,0}};
        int[][] b2 =  {{1,3,2},{4,5,6},{7,8,0}};

        assert (new Board(b1)).equals(new Board(b2)) == false;
    }

    @Test
    public void testisEqualTrue() {
        int[][] b1 =  {{1,2,3},{4,5,6},{7,8,0}};
        int[][] b2 =  {{1,2,3},{4,5,6},{7,8,0}};

        assert (new Board(b1)).equals(new Board(b2)) == true;
    }

    @Test
    public void testIteratorZeroInCenter() {
        int[][] b =  {{8,1,3},{4,0,2},{7,6,5}};

        int[][] b1 = {{8,0,3},{4,1,2},{7,6,5}};
        int[][] b2 = {{8,1,3},{4,6,2},{7,0,5}};
        int[][] b3 = {{8,1,3},{0,4,2},{7,6,5}};
        int[][] b4 = {{8,1,3},{4,2,0},{7,6,5}};
        Board[] neighbors = {new Board(b1), new Board(b2), new Board(b3), new Board(b4)};

        assert HasNeighbordBoards(new Board(b), neighbors);
    }

    @Test
    public void testIteratorZeroInTopRight() {
        int[][] b =  {{0,1,3},{4,8,2},{7,6,5}};

        int[][] b1 = {{4,1,3},{0,8,2},{7,6,5}};
        int[][] b2 = {{1,0,3},{4,8,2},{7,6,5}};
        Board[] neighbors = {new Board(b1), new Board(b2)};

        assert HasNeighbordBoards(new Board(b), neighbors);
    }

    @Test
    public void testIteratorZeroInBottomLeft() {
        int[][] b =  {{5,1,3},{4,8,2},{7,6,0}};

        int[][] b1 = {{5,1,3},{4,8,0},{7,6,2}};
        int[][] b2 = {{5,1,3},{4,8,2},{7,0,6}};
        Board[] neighbors = {new Board(b1), new Board(b2)};

        assert HasNeighbordBoards(new Board(b), neighbors);
    }

    private boolean HasNeighbordBoards(Board board, Board[] boards) {
        int count = 0;
        for(Board b:board.neighbors()) {
            boolean has = false;
            for(int i = 0; i < boards.length; i++) {
                if(b.equals(boards[i])) {
                    has = true;
                    break;
                }
            }
            if(!has) {
                return false;
            }
            count++;
        }

        if(count != boards.length) {
            return false;
        }
        return true;
    }
}
