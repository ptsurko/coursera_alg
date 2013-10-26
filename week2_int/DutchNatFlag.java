/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 10/20/13
 * Time: 12:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class DutchNatFlag {
    public static void main(String[] args) throws Exception {
        int[] array = createRandomArray();

        int zeroIndex = 0, twoIndex = array.length - 1;

        while(array[zeroIndex] == 0 && zeroIndex <= twoIndex) {
            zeroIndex++;
        }

        while(array[twoIndex] == 2 && twoIndex >= zeroIndex) {
            twoIndex--;
        }

        int index = zeroIndex;
        while(index != twoIndex) {
            if(array[index] == 0 && index != zeroIndex) {
                int temp = array[zeroIndex];
                array[zeroIndex] = array[index];
                array[index] = temp;

                zeroIndex++;
                index++;
            } else if(array[index] == 2 && index != twoIndex) {
                int temp = array[twoIndex];
                array[twoIndex] = array[index];
                array[index] = temp;

                twoIndex--;
            } else {
                index++;
            }
        }
    }

    private static int[] createRandomArray()
    {
        int[] array = new int[10];
        for(int i = 0; i < array.length; i++) {
            array[i] = StdRandom.uniform(3);
        }
        return array;
    }
}
