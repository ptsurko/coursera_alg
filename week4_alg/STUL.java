import java.util.NoSuchElementException;

//Symbol Table implemented via Unordered List
public class STUL<Key extends Comparable<Key>, Value> {
    private Node<Key, Value> head = new Node<Key, Value>();
    private int size;

    public STUL() {

    }

    public void Put(Key key, Value value) {
        Node<Key, Value> current = head;
        for(int i = 0; i < size; i++) {
            current = head.next;
            if(current.key.compareTo(key) == 0) {
                current.value = value;
                return;
            }
        }

        current.next = new Node<Key, Value>(key, value);
        size++;
    }

    public Value Get(Key key) throws NoSuchElementException {
        Node<Key, Value> current = head;
        for(int i = 0; i < size; i++) {
            current = current.next;
            if(current.key.compareTo(key) == 0) {
                return current.value;
            }
        }
        throw new NoSuchElementException();
    }

    public void Delete(Key key) {
        Node<Key, Value> current = head;
        for(int i = 0; i < size; i++) {
            if(current.next.key.compareTo(key) == 0) {
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
    }

    public boolean Contains(Key key) {
        Node<Key, Value> current = head;
        for(int i = 0; i < size - 1; i++) {
            current = current.next;
            if(current.key.compareTo(key) == 0) {
                return true;
            }
        }
        return false;
    }

    public boolean IsEmpty() {
        return Size() == 0;
    }

    public int Size() {
        return size;
    }

    private class Node<Key, Value> {
        public Key key;
        public Value value;
        public Node<Key, Value> next;

        public Node() {

        }

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public Node(Key key, Value value, Node<Key, Value> next) {
            this(key, value);

            this.next = next;
        }
    }
}
