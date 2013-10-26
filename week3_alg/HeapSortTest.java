import org.junit.Test;

public class HeapSortTest {
    @Test
    public void testHeapSort() {
        Integer[] items = new Integer[10];
        for(int i = 0; i < items.length; i++) {
            items[i] = 10 - i;
        }

        HeapSort.Sort(items);

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
