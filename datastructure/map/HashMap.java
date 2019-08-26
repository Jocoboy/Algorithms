package datastructure.map;

import java.util.Scanner;

class HashMap {

    private int hsize;
    private LinkedList[] buckets;

    public HashMap(int hsize) {
        buckets = new LinkedList[hsize];
        for (int i = 0; i < hsize; i++) {
            buckets[i] = new LinkedList();
        }
        this.hsize = hsize;
    }

    public int hashing(int key) {
        int hash = key % hsize;
        if (hash < 0) {
            hash += hsize;
        }
        return hash;
    }

    public void insertHash(int key) {
        buckets[hashing(key)].insert(key);
    }

    public void deleteHash(int key) {
        buckets[hashing(key)].delete(key);
    }

    public void displayHashTable() {
        for (int i = 0; i < hsize; i++) {
            System.out.printf("Bucket %d : ", i);
            buckets[i].display();
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        HashMap hashMap = new HashMap(10);

        while (true) {
            System.out.println("Enter your Choice :");
            System.out.println("1. Add Key");
            System.out.println("2. Delete Key");
            System.out.println("3. Print Table");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
            case 1: {
                System.out.println("Enter the Key: ");
                hashMap.insertHash(scanner.nextInt());
                break;
            }
            case 2: {
                System.out.println("Enter the Key delete:  ");
                hashMap.deleteHash(scanner.nextInt());
                break;
            }
            case 3: {
                System.out.println("Print table");
                hashMap.displayHashTable();
                break;
            }
            case 4:
                scanner.close();
                return;
            }
        }
    }
}