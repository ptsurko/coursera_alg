import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 10/26/13
 * Time: 1:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node<Key, Value> root = null;

    public BST() {

    }

    public void Put(Key key, Value value) {
        if(root == null) {
            root = new Node<Key, Value>(key, value);
            root.size = 1;
        } else {
            PutIntoSubtree(root, key, value);
        }
    }

    private void PutIntoSubtree(Node<Key, Value> root, Key key, Value value) {
        if(key.compareTo(root.key) == 0) {
            root.value = value;
        } else if(key.compareTo(root.key) < 0) {
            if(root.left == null) {
                root.left = new Node<Key, Value>(key, value);
            } else {
                PutIntoSubtree(root.left, key, value);
            }
        } else {
            if(root.right == null) {
                root.right = new Node<Key, Value>(key, value);
            } else {
                PutIntoSubtree(root.right, key, value);
            }
        }
        root.size = 1 + Size(root.left) + Size(root.right);
    }

    public Value Get(Key key) throws NoSuchElementException {
        Node<Key, Value> current = root;
        while(true) {
            if(current == null) {
                throw new NoSuchElementException();
            }
            if(key.compareTo(current.key) == 0) {
                return current.value;
            } else if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
    }

    public Key Max() {
        Node<Key, Value> current = root;
        while(current.right != null) {
            current = current.right;
        }
        return current.key;
    }

    public Key Min() {
        Node<Key, Value> current = root;
        while(current.left != null) {
            current = current.left;
        }
        return current.key;
    }

    //Largest key <= key;
    public Key Floor(Key key) {
        Key max = null;
        Node<Key, Value> current = root;
        while(true) {
            if(current == null) {
                if(max == null) {
                    throw new NoSuchElementException();
                }
                return max;
            }
            if(current.key == key) {
                return key;
            } else if(key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                if(max == null || max.compareTo(current.key) < 0) {
                    max = current.key;
                }
                current = current.right;
            }
        }
    }

    public Key Ceiling(Key key) throws Exception {
        throw new Exception();
    }

    public int Size() {
        return Size(root);
    }

    private int Size(Node<Key, Value> node) {
        return node == null ? 0 : node.size;
    }

    //number of keys < K
    public int Rank(Key key) {
        int result = 0;
        Node<Key, Value> current = root;
        while(true) {
            if(current == null) {
                break;
            } if (current.key.compareTo(key) == 0) {
                return result + Size(current.left);
            } else if(current.key.compareTo(key) < 0) {
                result += current.size;
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return result;
    }

    private class Node<Key, Value> {
        public Node(Key key, Value value) {
            this(key, value, null, null);
        }

        public Node(Key key, Value value, Node<Key, Value> left, Node<Key, Value> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;

            this.size = 1;
        }

        public Key key;
        public Value value;
        public Node<Key, Value> left;
        public Node<Key, Value> right;
        public int size;
    }
}
