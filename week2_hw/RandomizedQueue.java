import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static Random random = new Random();
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

        int index = 0;
        if(size > 0) {
            index = random.nextInt(size + 1);

            items[size] = items[index];
        }

        size++;
        items[index] = item;
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

        int indexToReturn = random.nextInt(size);
        Item itemToReturn = items[--size];
        items[indexToReturn] = items[size];
        items[size] = null;
        
        if(size > 0 && items.length / 4 == size) {
            resize(items.length / 2);
        }

        return itemToReturn;
    }

    public Item sample() {              // return (but do not delete) a random item
        if(size == 0) {
            throw new NoSuchElementException();
        }


        return items[random.nextInt(size)];
    }
    public Iterator<Item> iterator() {  // return an independent iterator over items in random order
        return new RandomizedQueueIterator(this.items, this.size);
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] items;
        private int index = 0;

        public RandomizedQueueIterator(Item[] items, int size){
            this.items = (Item[]) new Object[size];
            this.index = size;

            for (int i = 0; i < size; i++) {
                this.items[i] = items[i];
            }
            
            //StdRandom.shuffle(this.items);
        }
        public boolean hasNext() {
            return index > 0;
        }
        public Item next() {
            if(index == 0) {
                throw new NoSuchElementException();
            }

            return this.items[--index];
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}