import org.junit.Test;

public class BSTTest {
    @Test
    public void testBSTPut() {
        BST<String, Integer> bst = new BST<String, Integer>();
        bst.Put("3", 3);
        bst.Put("1", 1);
        bst.Put("4", 4);

        assert bst.Get("1") == 1;
        assert bst.Get("4") == 4;
        assert bst.Get("3") == 3;
    }


    @Test
    public void testMinMax() {
        BST<String, Integer> bst = new BST<String, Integer>();
        bst.Put("3", 3);
        bst.Put("1", 1);
        bst.Put("4", 4);

        assert bst.Min() == "1";
        assert bst.Max() == "4";
    }

    @Test
    public void testSize() {
        BST<String, Integer> bst = new BST<String, Integer>();
        bst.Put("3", 3);
        bst.Put("1", 1);
        assert bst.Size() == 2;

        bst.Put("4", 4);
        assert bst.Size() == 3;
    }

    @Test
    public void testFloor() {
        BST<String, Integer> bst = new BST<String, Integer>();
        bst.Put("S", 3);
        bst.Put("E", 3);
        bst.Put("A", 3);
        bst.Put("C", 3);
        bst.Put("R", 3);
        bst.Put("H", 3);
        bst.Put("M", 3);
        bst.Put("X", 3);

        assert bst.Floor("G") == "E";
        assert bst.Floor("D") == "C";
        assert bst.Floor("C") == "C";
        assert bst.Floor("Z") == "X";
    }

    @Test
    public void testRank() {
        BST<String, Integer> bst = new BST<String, Integer>();
        bst.Put("S", 3);
        bst.Put("E", 3);
        bst.Put("A", 3);
        bst.Put("C", 3);
        bst.Put("R", 3);
        bst.Put("H", 3);
        bst.Put("M", 3);
        bst.Put("X", 3);

        assert bst.Rank("X") == 8;
        assert bst.Rank("A") == 0;
    }

    @Test
    public void testDelete() {
        BST<String, Integer> bst = new BST<String, Integer>();
        bst.Put("S", 3);
        bst.Put("E", 3);
        bst.Put("A", 3);
        bst.Put("C", 3);
        bst.Put("R", 3);
        bst.Put("H", 3);
        bst.Put("M", 3);
        bst.Put("X", 3);

        assert bst.Size() == 8;
        bst.Delete("E");

        assert bst.Size() == 7;

        bst.Delete("S");
        assert bst.Size() == 6;

        bst.Delete("C");
        assert bst.Size() == 5;
    }

}
