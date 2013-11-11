import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 11/9/13
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class RedBlackBST2Test {
    @Test
    public void testPutComplex1() {
        RedBlackBST2<String, String> rb = new RedBlackBST2<String, String>();
        rb.Put("c", "c");
        rb.Put("e", "e");
        rb.Put("r", "r");
        rb.Put("a", "a");
        rb.Put("s", "s");
        rb.Put("h", "h");

        assert rb.isValid(rb);
    }


    @Test
    public void testPutComplex2() {
        RedBlackBST2<String, String> rb = new RedBlackBST2<String, String>();
        rb.Put("e", "e");
        rb.Put("r", "r");
        rb.Put("h", "h");

        assert rb.isValid(rb);
    }
}
