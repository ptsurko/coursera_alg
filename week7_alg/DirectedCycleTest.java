import org.junit.Test;

public class DirectedCycleTest {

    @Test
    public void testCycle() {
        Digraph g = new Digraph(6);
        g.addEdge(0, 5);
        g.addEdge(3, 5);
        g.addEdge(5, 4);
        g.addEdge(4, 3);

        DirectedCycle dc = new DirectedCycle(g);

        assert dc.hasCycle() == true;
    }

    @Test
    public void testCycle2() {
        Digraph g = new Digraph(6);
        g.addEdge(1, 0);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 0);


        DirectedCycle dc = new DirectedCycle(g);

        assert dc.hasCycle() == false;
    }


    @Test
    public void testCycle3() {
        Digraph g = new Digraph(6);
        g.addEdge(0, 1);
        g.addEdge(2, 1);
        g.addEdge(3, 2);
        g.addEdge(4, 3);
        g.addEdge(5, 4);
        g.addEdge(0, 5);

        DirectedCycle dc = new DirectedCycle(g);

        assert dc.hasCycle() == false;
    }
}
