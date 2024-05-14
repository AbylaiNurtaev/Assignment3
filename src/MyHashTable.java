/**
 * Implementation of a simple hash table data structure.
 * @param <K> The type of keys stored in the hash table.
 * @param <V> The type of values stored in the hash table.
 */
public class MyHashTable<K, V> {

    /**
     * Inner class representing a node in the hash table.
     */
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        /**
         * Constructs a new HashNode with the given key and value.
         * @param key The key of the node.
         * @param value The value of the node.
         */
        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }

        /**
         * Returns a string representation of the HashNode.
         * @return A string representation of the HashNode.
         */
        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }

    }

    private HashNode<K, V>[] chainArray; // Array to hold the chains of nodes
    private int M = 11; // Default number of chains
    private int size; // Number of elements in the hash table

    /**
     * Constructs an empty hash table with default number of chains.
     */
    public MyHashTable(){}

    /**
     * Constructs an empty hash table with the specified number of chains.
     * @param M The number of chains in the hash table.
     */
    public MyHashTable(int M){
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;
    }

    /**
     * Generates a hash code for the given key.
     * @param key The key to be hashed.
     * @return The hash code for the key.
     */
    public int hash(K key){
        int hashCode = 0;
        int prime = 31;

        for (int i = 0; i < key.toString().length(); i++){
            hashCode = hashCode * prime + key.toString().charAt(i);
        }
        hashCode = hashCode % M;
        if(hashCode < 0){
            hashCode += M;
        }

        return hashCode;
    }

    /**
     * Inserts a key-value pair into the hash table.
     * @param key The key to insert.
     * @param value The value associated with the key.
     */
    public void put(K key, V value){
        int hashCode = hash(key);
        HashNode<K, V> current = chainArray[hashCode];

        while (current != null){
            if(current.key.equals(key)){
                current.value = value;
                return;
            }
            current = current.next;
        }

        HashNode<K, V> newNode = new HashNode<>(key, value);
        newNode.next = chainArray[hashCode];
        chainArray[hashCode] = newNode;

        size++;
    }

    /**
     * Retrieves the value associated with the specified key.
     * @param key The key whose associated value is to be retrieved.
     * @return The value associated with the specified key, or null if the key is not found.
     */
    public V get(K key){
        int hashCode = hash(key);
        HashNode<K, V> current = chainArray[hashCode];
        while (current != null){
            if(current.key.equals(key)){
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Removes the key-value pair with the specified key from the hash table.
     * @param key The key of the key-value pair to be removed.
     * @return The value associated with the specified key, or null if the key is not found.
     */
    public V remove(K key){
        int hashCode = hash(key);

        HashNode<K, V> current = chainArray[hashCode];
        HashNode<K, V> prev = null;

        while (current != null){
            if(current.key.equals(key)){
                if(prev != null){
                    prev.next = current.next;
                }else{
                    chainArray[hashCode] = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    /**
     * Checks if the hash table contains the specified value.
     * @param value The value to check for.
     * @return true if the value is found in the hash table, false otherwise.
     */
    public boolean contains(V value){
        for(int i = 0; i < chainArray.length; i++){
            HashNode<K, V> current = chainArray[i];

            while (current != null){
                if(current.value.equals(value)){
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    /**
     * Retrieves the key associated with the specified value.
     * @param value The value whose associated key is to be retrieved.
     * @return The key associated with the specified value, or null if the value is not found.
     */
    public K getKey(V value){
        for(int i = 0; i < chainArray.length; i++){
            HashNode<K, V> current = chainArray[i];
            while (current != null) {
                if(current.value.equals(value)){
                    return current.key;
                }
                current = current.next;
            }

        }
        return null;
    }
}
