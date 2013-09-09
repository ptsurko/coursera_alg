import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head, tail;
    private int size = 0;
    private DequeIterator dequeIterator = new DequeIterator();

    public Deque() {                  // construct an empty deque
    }
    public boolean isEmpty() {          // is the deque empty?
        return size == 0;
    }
    public int size() {                 // return the number of items on the deque
        return size;
    }
    public void addFirst(Item item) {   // insert the item at the front]
        if(item == null) {
            throw new NullPointerException();
        }

        if(size == 0) {
            head = tail = new Node<Item>(item, null, null);
        } else {
            Node<Item> newHead = new Node(item, head, null);
            head.prev = newHead;
            head = newHead;
        }

        size++;
    }
    public void addLast(Item item) {    // insert the item at the end
        if(item == null) {
            throw new NullPointerException();
        }

        if(size == 0) {
            head = tail = new Node<Item>(item, null, null);
        } else {
            Node<Item> newTail = new Node(item, null, tail);
            tail.next = newTail;
            tail = newTail;
        }

        size++;
    }
    public Item removeFirst() {         // delete and return the item at the front
        if(head == null) {
            throw new NoSuchElementException();
        }
        size--;

        Item itemToReturn = head.item;
        head = head.next;
        if(head == null) {
            tail = null;
        } else {
            head.prev = null;
        }

        return itemToReturn;
    }
    public Item removeLast() {          // delete and return the item at the end
        if(tail == null) {
            throw new NoSuchElementException();
        }
        size--;

        Item itemToReturn = tail.item;
        tail = tail.prev;
        if(tail == null) {
            head = null;
        } else {
            tail.next = null;
        }

        return itemToReturn;
    }

    public Iterator<Item> iterator() {  // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        Node<Item> current = head;
        public boolean hasNext() {
            return current != null;
        }
        public Item next() {
            if(current == null) {
                throw new NoSuchElementException();
            }
            Item itemToReturn = current.item;
            current = current.next;
            return itemToReturn;
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node<T> {
        public Node() {
            this(null, null, null);
        }

        public Node(T item, Node<T> next, Node<T> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        public T item;
        public Node<T> next;
        public Node<T> prev;
    }
}
