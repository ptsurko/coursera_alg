/**
 * В массиве интов есть число, которое встречается больше половины раз. 
 * Как за O(1) памяти и за один проход по массиву найти это число?
 * @author ptsurko
 */
public class ArrayMajority {
	private int[] array;
	
	public ArrayMajority(int[] array) {
		this.array = array;
	}
	
	public int Find() {
		int candidate = 0;
		int candidateCount = 0;
		
		for(int i = 0; i < array.length; i++) {
			if (candidate == array[i]) {
				candidateCount++;
			} else if (candidateCount > 1) {
				candidateCount--;
			} else {
				candidate = array[i];
				candidateCount = 1;
			}
		}
		
		return candidate;
	}
}
