import junit.framework.Assert;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 10/11/13
 * Time: 8:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class RandomizedQueueTest {
    @Test
    public void testInsert() {
        RandomizedQueue<Object> queue = new RandomizedQueue<Object>();
        queue.enqueue(new Object());
        queue.enqueue(new Object());
        queue.enqueue(new Object());

        Assert.assertEquals(queue.size() ,3);
    }

    @Test
    public void testRemove() {
        RandomizedQueue<Object> queue = new RandomizedQueue<Object>();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);


        Object a = queue.dequeue();
        Object b = queue.dequeue();
        Object c = queue.dequeue();

        queue.enqueue(10);

        Assert.assertEquals(queue.size(), 1);
    }
    @Test
    public void testIterator() {
        RandomizedQueue<Object> queue = new RandomizedQueue<Object>();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        Iterator<Object> iterator = queue.iterator();
        Iterator<Object> iterator2 = queue.iterator();
        while(iterator.hasNext()) {
            Object o1 = iterator.next();
            Object o2 = iterator2.next();
        }

        Assert.assertEquals(false, iterator.hasNext());
        Assert.assertEquals(false, iterator2.hasNext());
    }
}
