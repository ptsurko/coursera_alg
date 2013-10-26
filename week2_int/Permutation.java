/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 10/20/13
 * Time: 11:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class Permutation {
    public static void main(String[] args) throws Exception {
        int[] array1 = createRandomArray();
        int[] array2 = createRandomArray();

        if(array1.length != array2.length) {
            throw new Exception("Arrays are not identical");
        }

        for(int i = 0; i < array1.length; i++) {

            int min1 = array1[i], minIndex1 = i, min2 = array2[i], minIndex2 = i;
            for(int j = i + 1; j < array1.length; j++) {
                if(array1[j] < min1) {
                    min1 = array1[j];
                    minIndex1 = j;
                }
            }

            for(int j = i + 1; j < array2.length; j++) {
                if(array2[j] < min2) {
                    min2 = array2[j];
                    minIndex2 = j;
                }
            }

            if(min1 != min2) {
                throw new Exception("Arrays are not identical");
            }

            while(minIndex1 - 1 >= i) {
                int temp = array1[minIndex1 - 1];
                array1[minIndex1 - 1] = array1[minIndex1];
                array1[minIndex1] = temp;
                minIndex1--;
            }

            while(minIndex2 - 1 >= i) {
                int temp = array2[minIndex2 - 1];
                array2[minIndex2 - 1] = array2[minIndex2];
                array2[minIndex2] = temp;
                minIndex2--;
            }
        }
        System.out.println("Arrays are identical");
    }



//    private static int[] createRandomArray()
//    {
//        int[] array = new int[10];
//        for(int i = 0; i < array.length; i++) {
//            array[i] = StdRandom.uniform(20);
//        }
//        return array;
//    }

    private static int[] createRandomArray()
    {
        int[] array = new int[10];
        for(int i = 0; i < array.length; i++) {
            array[i] = StdRandom.uniform(15);
        }
        return array;
    }
}
