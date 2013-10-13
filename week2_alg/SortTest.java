import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 10/12/13
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class SortTest {
    @Test
    public void testSelectionSort() {
        Integer[] items = new Integer[10];
        for(int i = 0; i < items.length; i++) {
            items[i] = 10 - i;
        }

        SelectionSort.Sort(items);

        assert isSorted(items);
    }

    @Test
    public void testInsertionSort() {
        Integer[] items = new Integer[10];
        for(int i = 0; i < items.length; i++) {
            items[i] = 10 - i;
        }

        InsertionSort.Sort(items);

        assert isSorted(items);
    }

    @Test
    public void testMergeSort() {
        Integer[] items = new Integer[10];
        for(int i = 0; i < items.length; i++) {
            items[i] = 10 - i;
        }

        MergeSort.Sort(items);

        assert isSorted(items);
    }

    private static boolean isSorted(Comparable[] items) {
        for(int i = 1; i < items.length; i++) {
            if(items[i].compareTo(items[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }
}
