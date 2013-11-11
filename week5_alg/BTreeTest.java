import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 11/10/13
 * Time: 9:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class BTreeTest {
    @Test
    public void testPut() throws Exception {
        BTree<Integer, String> btree = new BTree<Integer, String>(4);
        btree.Put(1, "1");
        btree.Put(2, "2");
        btree.Put(3, "3");
        btree.Put(4, "4");
    }
}
