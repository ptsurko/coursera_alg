/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 10/12/13
 * Time: 3:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class SelectionSort {
    public static void Sort(Comparable[] items) {
        for(int i = 0; i < items.length; i++) {
            int minIndex = i;
            for(int j = i + 1; j < items.length; j++) {
                if(items[j].compareTo(items[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            Comparable minItem = items[minIndex];
            int index = minIndex;
            while(index > i) {
                items[index] = items[index - 1];
                index--;
            }

            items[index] = minItem;
        }
    }
}
