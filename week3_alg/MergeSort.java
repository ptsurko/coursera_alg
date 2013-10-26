/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 10/12/13
 * Time: 4:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class MergeSort {
    private static Comparable[] copy;
    public static void Sort(Comparable[] items) {
        copy = new Comparable[items.length];

        SortInternal(items, 0, items.length - 1);

        copy = null;
    }

    private static void SortInternal(Comparable[] items, int start, int end) {
        if(end > start) {
            int start1 = start, end1 = start + (end - start) / 2,
                start2 = end1 + 1, end2 = end;
            SortInternal(items, start1, end1);
            SortInternal(items, start2, end2);

            CopyItems(items, start, end);
            int index = start;
            while(index <= end) {
                if(start1 > end1) {
                    items[index++] = copy[start2++];
                } else if(start2 > end2) {
                    items[index++] = copy[start1++];
                } else if(copy[start1].compareTo(copy[start2]) < 0) {
                    items[index++] = copy[start1++];
                } else {
                    items[index++] = copy[start2++];
                }
            }
        }
    }

    private static void CopyItems(Comparable[] items, int start, int end) {
        for(int i = start; i <= end; i++) {
            copy[i] = items[i];
        }
    }
}
