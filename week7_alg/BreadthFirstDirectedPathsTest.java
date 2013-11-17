import org.junit.Test;

public class BreadthFirstDirectedPathsTest {

    @Test
    public void testNonExistingPath() {
        Digraph g = new Digraph(6);
        g.addEdge(1, 0);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 0);

        BreadthFirstDirectedPaths dp = new BreadthFirstDirectedPaths(g, 0);

        assert dp.hasPathTo(1) == false;
        assert dp.hasPathTo(2) == false;
        assert dp.hasPathTo(3) == false;
        assert dp.hasPathTo(4) == false;
        assert dp.hasPathTo(5) == false;
        assert dp.hasPathTo(0) == false;
    }


    @Test
    public void testExistingPath() {
        Digraph g = new Digraph(6);
        g.addEdge(0, 1);
        g.addEdge(2, 1);
        g.addEdge(3, 2);
        g.addEdge(4, 3);
        g.addEdge(5, 4);
        g.addEdge(0, 5);

        BreadthFirstDirectedPaths dp = new BreadthFirstDirectedPaths(g, 0);

        assert dp.hasPathTo(1) == true;
        assert dp.distTo(1) == 1;

        assert dp.hasPathTo(2) == true;
        assert dp.distTo(2) == 4;

        assert dp.hasPathTo(3) == true;
        assert dp.distTo(3) == 3;

        assert dp.hasPathTo(4) == true;
        assert dp.distTo(4) == 2;

        assert dp.hasPathTo(5) == true;
        assert dp.distTo(5) == 1;
    }
}
