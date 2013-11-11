/**
 * Created with IntelliJ IDEA.
 * User: Pavel_Tsurko
 * Date: 11/10/13
 * Time: 9:16 AM
 * To change this template use File | Settings | File Templates.
 */
public class BTree<Key extends Comparable<Key>, Value> {
    private int maxNodes;
    private Node root;

    public BTree(int maxNodes) throws Exception {
        if(maxNodes < 4) {
            throw new Exception();
        }
        this.maxNodes = maxNodes;
    }

    public BTree() throws Exception {
        this(4);
    }

    public void Put(Key key, Value value) {
        if(root == null) {
            root = new Node(true);
            root.children[root.childCount++] = new Children(null, null);
        }

        Children newChildren = new Children(key, value);
        Put(root, newChildren);

        if (root.childCount == maxNodes) {
            Node nodeLeft = root, nodeRight = Split(nodeLeft);

            root = new Node(false);
            InsertChildren(root, new Children(nodeLeft.children[0].key, nodeLeft));
            InsertChildren(root, new Children(nodeRight.children[0].key, nodeRight));
        }
    }

    public void Put(Node node, Children children) {
        if(node.isLeaf()) {
            InsertChildren(node, children);
        } else {
            int indexToPut = node.childCount - 1;
            for (int i = 0; i < node.childCount - 1; i++) {
                if(children.key.compareTo(node.children[i + 1].key) < 0) {
                    indexToPut = i;
                    break;
                }
            }

            Node nodeToInsertInto = node.children[indexToPut].node;
            Put(nodeToInsertInto, children);
            if (nodeToInsertInto.childCount == maxNodes) {
                Node nodeLeft = nodeToInsertInto, nodeRight = Split(nodeLeft);

                InsertChildren(node, new Children(nodeRight.children[0].key, nodeRight));
            }
        }
    }

    private void InsertChildren(Node node, Children children) {
        //TODO: consider replacing
        int index = node.childCount;
        while(index > 0 && node.children[index - 1].key != null && children.key.compareTo(node.children[index - 1].key) < 0 ) {
            node.children[index] = node.children[--index];
        }
        node.children[index] = children;
        node.childCount++;
    }

    private Node Split(Node nodeToSplit) {
        Node newNode = new Node(nodeToSplit.isLeaf);

        nodeToSplit.childCount = maxNodes / 2;
        for(int i = maxNodes / 2; i < maxNodes; i++) {
            newNode.children[newNode.childCount++] = nodeToSplit.children[i];
        }

        return newNode;
    }

    private class Node {
        private Boolean isLeaf;

        public Children[] children = new Children[maxNodes];
        public int childCount;

        public Node(Boolean isLeaf) {
            this.isLeaf = isLeaf;
        }

        public Boolean isLeaf() {
            return this.isLeaf;
        }
    }

    private class Children<Key extends Comparable<Key>, Value> {


        public Children(Key key, Node node) {
            this.key = key;
            this.node = node;
        }

        public Children(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        public Key key;
        public Value value;
        public Node node;

    }

//    private class MinChildren extends Children {
//        public MinChildren(Key key, Node node) {
//            this.node = node;
//        }
//    }
//
//    private class MaxChildren extends Children {
//        public MinChildren(Key key, Node node) { super(key, node); }
//        public MinChildren(Key key, Value value) { super(key, value); }
//    }
//
//    private class Leaf extends Children {
//
//    }

}
