import junit.framework.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: Pasha
 * Date: 9/9/13
 * Time: 10:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class DequeTest {
    @Test
    public void testIsEmpty() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        Assert.assertEquals(deque.isEmpty(), true);

        deque.addFirst(new Object());
        Assert.assertEquals(deque.isEmpty(), false);
    }

    @Test
    public void testSize() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        Assert.assertEquals(deque.size(), 0);

        deque.addFirst(new Object());
        Assert.assertEquals(deque.size(), 1);

        deque.removeLast();
        Assert.assertEquals(deque.size(), 0);
    }

    @Test
    public void testAddFirst() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        Object obj = new Object();
        deque.addFirst(obj);
        Assert.assertEquals(obj, deque.removeLast());
    }

    @Test
    public void testAddLast() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        Object obj = new Object();
        deque.addLast(obj);
        Assert.assertEquals(obj, deque.removeFirst());
    }

    @Test
    public void testAddFirst1000() throws Exception {
        Deque<Object> deque = new Deque<Object>();
        for(int i = 0; i < 1000; i++) {
            deque.addFirst(new Object());
        }

        int counter = 0;
        for(Object o : deque) {
            counter++;
        }

        Assert.assertEquals(1000, counter);

    }


    @Test
    public void testRemoveFirst() throws Exception {

    }

    @Test
    public void testRemoveLast() throws Exception {

    }

    @Test
    public void testIterator() throws Exception {

    }
}
