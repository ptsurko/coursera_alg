import org.junit.Test;

public class MaxPQTest {

    //@Test
    public void testMaxPQ() {
        MaxPQ<String> pq = new MaxPQ<String>(2);
        pq.insert("c");
        pq.insert("j");
        pq.insert("a");
        pq.insert("x");

        assert pq.deleteMax() == "x";
        assert pq.deleteMax() == "j";
        assert pq.deleteMax() == "c";
        assert pq.deleteMax() == "a";
    }
}
