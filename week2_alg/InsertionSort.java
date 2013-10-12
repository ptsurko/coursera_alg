/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 10/12/13
 * Time: 3:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class InsertionSort {
    public static void Sort(Comparable[] items) {
        for(int i = 1; i < items.length; i++) {
            int j = i;
            while(j > 0 && items[j].compareTo(items[j - 1]) < 0) {
                Comparable temp = items[j];
                items[j] = items[j - 1];
                items[j - 1] = temp;
                j--;
            }
        }
    }
}
