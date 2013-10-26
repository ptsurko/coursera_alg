/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 10/23/13
 * Time: 9:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class HeapSort {
    public static void Sort(Comparable[] items) {
        for(int i = items.length / 2 - 1; i > 0; i--) {
            sink(items, i, items.length - 1);
        }
        int n = items.length - 1;
        while(n > 0) {
            exch(items, n, 0);
            sink(items, 0, n--);
        }
    }

    private static void sink(Comparable[] items, int k, int n) {
        while(k <= n / 2) {
            int j = k * 2 + 1;
            if (j + 1 < n && items[j].compareTo(items[j + 1]) < 0) j++;
            if(j >= n || items[k].compareTo(items[j]) > 0) break;
            exch(items, k, j);
            k = j;
        }
    }

    private static void swim(Comparable[] items, int k) {
        while(k > 0 && items[getParentIndex(k)].compareTo(items[k]) < 0) {
            exch(items, k, getParentIndex(k));

            k = getParentIndex(k);
        }
    }

    private static int getParentIndex(int k) {
        if(k % 2 == 0) {
            return k / 2 - 1;
        } else {
            return k / 2;
        }
    }

    private static void exch(Comparable[] items, int a, int b) {
        Comparable temp = items[a];
        items[a] = items[b];
        items[b] = temp;
    }
}
