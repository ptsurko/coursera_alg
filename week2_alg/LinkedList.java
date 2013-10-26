/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 10/26/13
 * Time: 11:46 AM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;
    public LinkedList() {

    }

    public void Add(T value) {
        if(head == null) {
            head = tail = new Node(value);
        } else {
            tail.next = new Node(value);
            tail = tail.next;
        }
    }

    public void Insert(T value, int index) {
        if(index > size) {
            throw new IndexOutOfBoundsException();
        }

        if(index == 0) {
            Node<T> newHead = new Node(value, head);
            head = newHead;
        } else {
            Node<T> current = head;
            for(int i = 1; i < index; i++) {
                current = current.next;
            }
            current.next = new Node(value, current.next);
        }
    }

    public void Delete(int index) {
        if(index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if(index == 0) {
            head = head.next;
        } else {
            Node<T> current = head;
            for(int i = 1; i < index; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
    }



    public int size() {
        return size;
    }

    private class Node<T> {
        public Node(T value) {
            this.value = value;
        }
        public Node(T value, Node next) {
            this(value);
            this.next = next;
        }

        public T value;
        public Node next;
    }
}
