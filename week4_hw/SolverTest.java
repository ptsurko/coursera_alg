import org.junit.Test;

public class SolverTest {

    @Test
    public void testSolver0() {
        int[][] blocks = {{1,2,3},{4,5,6},{7,8,0}};
        Solver s = new Solver(new Board(blocks));
        s.isSolvable();
        s.solution();
        assert s.moves() == 0;
    }

    @Test
    public void testSolver4() {
        int[][] blocks = {{0,1,3},{4,2,5},{7,8,6}};
        Solver s = new Solver(new Board(blocks));
        s.solution();
        assert s.moves() == 4;
    }

    @Test
    public void testSolver9() {
        int[][] blocks = {{2,0,3,4},{1,10,6,8},{5,9,7,12}, {13,14,11,15}};
        Solver s = new Solver(new Board(blocks));
        s.solution();
        assert s.moves() == 9;
    }

    @Test
    public void testSolver20() {
        int[][] blocks = {{1,6,4},{7,0,8},{2,3,5}};
        Solver s = new Solver(new Board(blocks));
        s.solution();
        assert s.moves() == 20;
    }

    @Test
    public void testSolver26() {
        int[][] blocks = {{5,7,0},{3,2,8},{1,6,4}};
        Solver s = new Solver(new Board(blocks));
        s.solution();
        assert s.moves() == 26;
    }

    @Test
    public void testSolver30() {
        int[][] blocks = {{8,4,7},{1,5,6},{3,2,0}};
        Solver s = new Solver(new Board(blocks));
        s.solution();
        assert s.moves() == 30;
    }

    @Test
    public void testSolver4x4hard1() {
        int[][] blocks = {{5,4,3,8},{9,2,6,1},{0,13,14,7}, {15,11,10,12}};
        Solver s = new Solver(new Board(blocks));
        s.solution();
        assert s.moves() == 38;
    }

    @Test
    public void testUnsolvable() {
        int[][] blocks = {{1,0},{2,3}};
        Solver s = new Solver(new Board(blocks));
        assert s.isSolvable() == false;
    }
}
