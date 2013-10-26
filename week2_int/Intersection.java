/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 10/20/13
 * Time: 11:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class Intersection {
    public static void main(String[] args) {
        int[] array1 = createRandomArray();
        int[] array2 = createRandomArray();

        int[] result = new int[20];

        int resultCount = 0;
        for(int i = 0; i < array1.length; i++) {
            boolean wasFound = false;
            for(int j = 0; j < resultCount; j++) {
                if(array1[i] == result[j]) {
                    wasFound = true;
                    break;
                }
            }
            if(!wasFound) {
                result[resultCount++] = array1[i];
            }
        }

        for(int i = 0; i < array2.length; i++) {
            boolean wasFound = false;
            for(int j = 0; j < resultCount; j++) {
                if(array2[i] == result[j]) {
                    wasFound = true;
                    break;
                }
            }
            if(!wasFound) {
                result[resultCount++] = array2[i];
            }
        }
    }

    private static int[] createRandomArray()
    {
        int[] array = new int[10];
        for(int i = 0; i < array.length; i++) {
            array[i] = StdRandom.uniform(20);
        }
        return array;
    }
}
