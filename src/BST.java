import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> {
    private Node root;

    private class Node {
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
        public K getKey() {
            return key;
        }

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
     * Deletes the node with the specified key from the binary search tree.
     * @param key The key of the node to be deleted.
     */
    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                Node successor = min(node.right);
                node.key = successor.key;
                node.val = successor.val;
                node.right = deleteMin(node.right);
            }
        }

        return node;
    }

    private Node min(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    /**
     * Returns an iterable containing the keys of the binary search tree in ascending order.
     * @return An iterable containing the keys of the binary search tree in ascending order.
     */
    public Iterable<Node> iterator() {
        Stack<Node> stack = new Stack<>();
        ArrayList<Node> list = new ArrayList<>();
        Node current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            list.add(current);
            current = current.right;
        }

        return list;
    }

    private void inorder(Node node, Queue<K> keys) {
        if (node == null) {
            return;
        }
        inorder(node.left, keys);
        keys.add(node.key);
        inorder(node.right, keys);
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }
}
