public class MyHashTable <K, V>{
    private class HashNode<K, V> {
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }

    }

    private HashNode<K, V>[] chainArray;
    private int M = 11; // default number of chains
    private int size;

    public MyHashTable(){}

    public MyHashTable(int M){
        this.M = M;
        chainArray = new HashNode[M];
        size = 0;

    }

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
