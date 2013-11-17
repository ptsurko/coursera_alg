import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<T> implements Iterable<T> {
    public int count = 0;
    public Node root, current;
    public void Bag() {
        root = current = null;
    }

    public void add(T item) {
        if(root == null) {
            root = current = new Node(item);
        } else {
            current.next = new Node(item);
            current = current.next;
        }
        count++;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return count;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<T> {
        private Node current = root;

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    private class Node {
        public Node(T item) {
            this.item = item;
        }

        public T item;
        public Node next;
    }
}
