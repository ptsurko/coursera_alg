import java.util.Arrays;

/**
 * Problem**: Let's say you have a list of N+1 integers between 1 and N. 
 * You know there's at least one duplicate, but there might be more. 
 * For example, if N=3, your list might be 3, 1, 1, 3 or it might be 1, 3, 2, 2. 
 * Print out a number that appears in the list more than once. 
 * (That is, in the first example,you can print '1' or '3' -- you don't have to print both.)
 * @author ptsurko
 */

public class ArrayDuplicates {
	private int maxInt;
	private int[] array;
	public ArrayDuplicates(int[] array, int N) {
		this.maxInt = N;
		this.array = array;
	}
	
	/**
	 * time: O(n), memory: O(n)
	 */
	public int[] FindWithBooleanArray() {
		boolean[] repeatsOnce = new boolean[this.maxInt + 1];
		int[] result = new int[1];
		int resultCount = 0;
		for(int i = 0; i < array.length; i++) {
			int index = array[i];
			if (!repeatsOnce[index]) {
				repeatsOnce[index] = true;
			} else {
				result[resultCount++] = index;
				if (resultCount >= result.length) {
					result = resizeArray(result);
				}
			}
		}
		int[] returnResult = new int[resultCount];
		System.arraycopy(result, 0, returnResult, 0, resultCount);
		return returnResult;
	}
	
	/**
	 * time: O(n), memory: O(n/32)
	 */
	public int[] FindWithIntBytes() {
		final int bitsInInt = 4 * 8;
		int[] repeatsOnce = new int[(int)Math.ceil((double)this.maxInt / bitsInInt) + 1];
		for(int i = 0; i < repeatsOnce.length; i++) {
			repeatsOnce[i] = 0;
		}
		
		int resultCount = 0;
		int[] result = new int[1];
		
		for(int i = 0; i < array.length; i++) {
			int intIndex = (int)Math.floor((double)array[i] / bitsInInt);
			int bitIndex = array[i] % bitsInInt;
			
			if (!isBitSet(repeatsOnce[intIndex], bitIndex)) {
				repeatsOnce[intIndex] = setBit(repeatsOnce[intIndex], bitIndex);
			} else {
				result[resultCount++] = array[i];
				if (resultCount >= result.length) {
					result = resizeArray(result);
				}
			}
		}
		
		int[] returnResult = new int[resultCount];
		System.arraycopy(result, 0, returnResult, 0, resultCount);
		return returnResult;
	}
	
	private static boolean isBitSet(int integer, int bit) {
		return (integer & (int)Math.pow(2, bit)) == (int)Math.pow(2, bit);
	}
	
	private static int setBit(int integer, int bit) {
		return integer | (int)Math.pow(2, bit);
	}
	
	/**
	 * time: O(nlog(n)), memory: O(1)
	 */
	public int[] FindWithSort() {
		int resultCount = 0;
		int[] result = new int[1];
		
		Arrays.sort(this.array);
		for(int i = 0; i < this.array.length - 1; i++) {
			if (array[i] == array[i + 1]) {
				result[resultCount++] = array[i];
				if (resultCount >= result.length) {
					result = resizeArray(result);
				}
			}
		}
		int[] returnResult = new int[resultCount];
		System.arraycopy(result, 0, returnResult, 0, resultCount);
		return returnResult;
	}
	
	public int[] FindWithBinarySearch() {
		int resultCount = 0;
		int[] result = new int[1];
		
		//TODO: Implement next algorithm and understand why it works
		// I think I can binary search for a duplicated number. 
		// For example, I go through the list and count the number 
		// of integers between 1 and N/2. If the count is greater 
		// than the number of possible integers in that range, then 
		// I know there's a duplicate in that range. Otherwise, a duplicate 
		// must exist in the range of N/2+1 to N. Once I know which half of 
		// the range the duplicate is in, I can recurse and binary search in 
		// that half, then keep repeating the process until I've found a duplicated 
		// number. The time complexity is O(n*log n) and the space complexity is O(1).
		
		int[] returnResult = new int[resultCount];
		System.arraycopy(result, 0, returnResult, 0, resultCount);
		return returnResult;
	}
	
	private static int[] resizeArray(int[] array) {
		int[] result = new int[array.length * 2];
		System.arraycopy(array, 0, result, 0, array.length);
		return result;
	}
}
