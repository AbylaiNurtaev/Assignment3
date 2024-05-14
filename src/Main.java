
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
    }
}