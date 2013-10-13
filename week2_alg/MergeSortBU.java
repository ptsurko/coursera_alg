/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 10/12/13
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class MergeSortBU {
    private static Comparable[] copy;

    public static void Sort(Comparable[] items) {
        copy = new Comparable[items.length];
        for(int step = 1; step < items.length; step *= 2){
            for(int start = 0; start < items.length + step; start += step * 2) {
                MergeInternal(items, start, start + step - 1, start + step, Math.min(start + step * 2 - 1, items.length - 1));
            }
        }
        copy = null;
    }

    private static void MergeInternal(Comparable[] items, int start1, int end1, int start2, int end2) {
        if(end2 > start1) {
            CopyItems(items, start1, end2);
            int index = start1;
            while(index <= end2) {
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