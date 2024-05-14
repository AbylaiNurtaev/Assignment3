
public class Main {
    public static void main(String[] args) {
        MyHashTable<String, Integer> hashTable = new MyHashTable<>(5);
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.put("three", 3);


        System.out.println(hashTable.get("two"));

        System.out.println(hashTable.contains(5));
        System.out.println(hashTable.contains(2));

        System.out.println(hashTable.remove("two"));

        System.out.println(hashTable.getKey(3));


        BST<Integer, String> tree = new BST<>();
        tree.put(5, "apple");
        tree.put(2, "banana");
        tree.put(8, "orange");
        tree.put(1, "grape");
        tree.put(4, "peach");

        for (BST.Node node : tree) {
            System.out.println("key is " + node.getKey() + " and value is " + node.getValue());
        }

    }
}