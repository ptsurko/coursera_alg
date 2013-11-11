import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 11/9/13
 * Time: 8:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class RedBlackBSTTest {
    @Test
    public void testPut() {
        RedBlackBST<Integer, String> rb = new RedBlackBST<Integer, String>();
        rb.Put(2, "2");
        rb.Put(1, "1");
        rb.Put(3, "3");
        rb.Put(0, "0");

        assert rb.Get(1) == "1";
        assert rb.Get(2) == "2";
        assert rb.Get(3) == "3";
        assert rb.Get(0) == "0";
        assert rb.Get(-1) == null;
    }

    @Test
    public void testPutComplex() {
        RedBlackBST<String, String> rb = new RedBlackBST<String, String>();
        rb.Put("c", "c");
        rb.Put("e", "e");
        rb.Put("r", "r");
        rb.Put("a", "a");
        rb.Put("s", "s");
        rb.Put("h", "h");

        assert rb.isValid(rb);
    }
}
