import junit.framework.*;
import org.junit.Test;

public class ArrayMajorityTest {
    
	@Test
    public void testArrayMajority() {
		int[] array = new int[] {1,2,1,4,1,4,1,2,3,4,5,1,1,1,1,1};
    	ArrayMajority am = new ArrayMajority(array);
    	
    	Assert.assertEquals(1, am.Find());
    }
}