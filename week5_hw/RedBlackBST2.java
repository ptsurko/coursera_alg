/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 11/9/13
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class RedBlackBST2<Key extends Comparable<Key>, Value> {

    private Node root;

    public RedBlackBST2() {

    }

    public void Put(Key key, Value value) {
        root = Put(root, key, value);
        root.isRed = false;
    }

    public Value Get(Key key) {
        Node current = root;

        while(current != null) {
            if(key.compareTo(current.key) == 0) {
                return current.value;
            } else if(key.compareTo(current.key) > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return null;
    }

    private Node Put(Node parent, Key key, Value value) {
        if(parent == null) {
            return new Node(key, value, true);
        }

        if(key.compareTo(parent.key) > 0) {
            parent.right = Put(parent.right, key, value);
        } else if(key.compareTo(parent.key) < 0) {
            parent.left = Put(parent.left, key, value);
        } else {
            parent.value = value;
        }
        Node result = parent;
//        if (isRed(result.right) && !isRed(result.left)) {
//            result = rotateLeft(result);
//        }

        if (isRed(result.right)) {
            if(isRed(result.right.left)) {
                result.right = rotateRight(result.right);
            }
            if(isRed(result.right.right)) {
                result = rotateLeft(result);
            }
        }
        if (isRed(result.left)) {
            if(isRed(result.left.right)) {
                result.left = rotateLeft(result.left);
            }
            if (isRed(result.left.left)) {
                result = rotateRight(result);
            }
        }
        if (isRed(result.right) && isRed(result.left)) {
            result = flipColors(result);
        }

        return result;
    }

    private boolean isRed(Node node) {
        return node != null && node.isRed;
    }

    private Node rotateLeft(Node node) {
        assert node.right.isRed;

        Node x = node.right;
        node.right = x.left;
        x.left = node;
        node.isRed = true;
        x.isRed = node.isRed;

        return x;
    }

    private Node rotateRight(Node node) {
        assert node.left.isRed;

        Node x = node.left;
        node.left = x.right;
        x.isRed = node.isRed;
        x.right = node;
        node.isRed = true;

        return x;
    }

    private Node flipColors(Node node) {
        assert node.right.isRed;
        assert node.left.isRed;

        node.isRed = true;
        node.right.isRed = false;
        node.left.isRed = false;

        return node;
    }

    private class Node {

        public Node(Key key, Value value, boolean isRed) {
            this.key = key;
            this.value = value;
            this.isRed = isRed;
        }

        public Node(Key key, Value value) {
            this(key, value, false);
        }

        public Key key;
        public Value value;
        public Node right;
        public Node left;
        public boolean isRed;
    }

    public boolean isValid(RedBlackBST2 rb) {
        return isValid(rb.root);
    }

    private boolean isValid(Node node) {
        if (node == null) {
            return true;
        } else if((isRed(node.left) && isRed(node.right)) ||
                  (isRed(node.left) && (isRed(node.left.left) || isRed(node.left.right))) ||
                  (isRed(node.right) && (isRed(node.right.left) || isRed(node.right.right)))) {
            return false;
        }
        return isValid(node.left) || isValid(node.right);
    }
}