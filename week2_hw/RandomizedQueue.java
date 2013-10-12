import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size = 0;
    private Item[] items;

    public RandomizedQueue() {          // construct an empty randomized queue
        items = (Item[]) new Object[2];
    }
    public boolean isEmpty() {          // is the queue empty?
        return size == 0;
    }
    public int size() {                 // return the number of items on the queue
        return size;
    }
    public void enqueue(Item item) {    // add the item
        if(item == null) {
            throw new NullPointerException();
        }

        if(items.length == size) {
            resize(items.length * 2);
        }
        items[size++] = item;
    }

    private void resize(int newSize) {
        Item[] newItems = (Item[]) new Object[newSize];
        for(int i = 0; i < size; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    public Item dequeue() {             // delete and return a random item
        if(size == 0) {
            throw new NoSuchElementException();
        }

        int randomIndex = StdRandom.uniform(size);


        return null;
    }
    public Item sample() {              // return (but do not delete) a random item
        if(size == 0) {
            throw new NoSuchElementException();
        }

        int randomIndex = StdRandom.uniform(size);
        return items[randomIndex];
    }
    public Iterator<Item> iterator() {  // return an independent iterator over items in random order
        return null;
    }
}