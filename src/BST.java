import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Node> {
    private Node root;

    /**
     * Inner class representing a node in the binary search tree.
     */
    public class Node {
        private K key;
        private V val;
        private Node left, right;

        /**
         * Constructs a new Node with the given key and value.
         * @param key The key of the node.
         * @param val The value of the node.
         */
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

        /**
         * Returns the key of the node.
         * @return The key of the node.
         */
        public K getKey() {
            return key;
        }

        /**
         * Returns the value of the node.
         * @return The value of the node.
         */
        public V getValue() {
            return val;
        }
    }

    /**
     * Inserts a key-value pair into the binary search tree.
     * @param key The key to insert.
     * @param val The value associated with the key.
     */
    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node node, K key, V val) {
        if (node == null) {
            return new Node(key, val);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.val = val; // Update value if key already exists
        }
        return node;
    }

    /**
     * Retrieves the value associated with the specified key.
     * @param key The key whose associated value is to be retrieved.
     * @return The value associated with the specified key, or null if the key is not found.
     */
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node.val;
        }
    }

    /**
     * Returns an iterator over elements of type {@code Node}.
     * @return an Iterator.
     */
    @Override
    public Iterator<BST.Node> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<BST.Node> {
        private Queue<Node> queue;

        public BSTIterator() {
            queue = new LinkedList<>();
            inorder(root);
        }

        private void inorder(Node node) {
            if (node == null) return;
            inorder(node.left);
            queue.add(node);
            inorder(node.right);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public BST.Node next() {
            if (!hasNext()) throw new NoSuchElementException();
            return queue.poll();
        }
    }
}
