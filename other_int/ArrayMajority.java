
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
