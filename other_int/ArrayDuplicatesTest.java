import org.junit.Test;
import org.junit.*;

public class ArrayDuplicatesTest {
	
	@Test
    public void testArrayOneDuplicateWithBoolean() {
		int[] array = new int[] {1,2,3,4,5,5};
		ArrayDuplicates ad = new ArrayDuplicates(array, 5);	

    	Assert.assertArrayEquals(new int[] {5}, ad.FindWithBooleanArray());
    }
	
	@Test
    public void testArrayManyDuplicatesWithBoolean() {
		int[] array = new int[] {1,2,3,4,5,5,4,3,2,1};
		ArrayDuplicates ad = new ArrayDuplicates(array, 9);	

    	Assert.assertArrayEquals(new int[] {5, 4, 3, 2, 1}, ad.FindWithBooleanArray());
    }
	
	@Test
    public void testArrayOneDuplicateWithInt() {
		int[] array = new int[] {1,2,3,4,5,5};
		ArrayDuplicates ad = new ArrayDuplicates(array, 5);	

    	Assert.assertArrayEquals(new int[] {5}, ad.FindWithIntBytes());
    }
	
	@Test
    public void testArrayManyDuplicateWithInt() {
		int[] array = new int[] {1,2,3,4,5,5,4,3,2,1};
		ArrayDuplicates ad = new ArrayDuplicates(array, 9);	

    	Assert.assertArrayEquals(new int[] {5, 4, 3, 2, 1}, ad.FindWithIntBytes());
    }
	
	@Test
    public void testArrayManyDuplicateWithIntWhereMoreThanOneIntUsed() {
		int[] array = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,68,69,70,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,68,69,70};
		ArrayDuplicates ad = new ArrayDuplicates(array, 139);	

    	Assert.assertArrayEquals(new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,68,69,70}, ad.FindWithIntBytes());
    }
	
	@Test
    public void testArrayManyDuplicateWithSort() {
		int[] array = new int[] {1,2,3,4,5,5,4,3,2,1};
		ArrayDuplicates ad = new ArrayDuplicates(array, 9);	

    	Assert.assertArrayEquals(new int[] {1,2,3,4,5}, ad.FindWithSort());
    }
}