import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 10/18/13
 * Time: 11:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class PointTest {
    @Test
    public void testIsEmpty() throws Exception {
        Point p = new Point(0, 3);
        Point q = new Point(0, 2);
        Point r = new Point(0, 1);

        assert p.SLOPE_ORDER.compare(q, r) == 0;
        //assert p.slopeTo(q) == -1.0;
        //assert p.slopeTo(r) == -1.0;

    }
}
