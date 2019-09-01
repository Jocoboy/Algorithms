package datastructure.tree;

import java.util.Scanner;

public class Trie {

    class TrieNode {
        TrieNode[] childNodes;
        boolean end;

        public TrieNode() {
            childNodes = new TrieNode[26];
            end = false;
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String s) {
        TrieNode curNode = root;
        for (int i = 0; i < s.length(); i++) {
            TrieNode node = curNode.childNodes[s.charAt(i) - 'a'];
            if (node == null) {
                node = new TrieNode();
                curNode.childNodes[s.charAt(i) - 'a'] = node;
            }
            curNode = node;
        }
        curNode.end = true;
    }

    public boolean delete(String s) {
        TrieNode curNode = root;
        for (int i = 0; i < s.length(); i++) {
            TrieNode node = curNode.childNodes[s.charAt(i) - 'a'];
            if (node == null) {
                return false;
            }
            curNode = node;
        }
        if (curNode.end == true) {
            curNode.end = false;
            return true;
        }
        return false;
    }

    public boolean search(String s) {
        TrieNode curNode = root;
        for (int i = 0; i < s.length(); i++) {
            TrieNode node = curNode.childNodes[s.charAt(i) - 'a'];
            if (node == null) {
                return false;
            }
            curNode = node;
        }
        return curNode.end;
    }

    public static boolean isValidS(String s) {
        return s.matches("^[a-z]+$");
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        Scanner Scanner = new Scanner(System.in);
        while (true) {
            String s;
            System.out.println("1. Insert\n2. Delete\n3. Search\n4. Exit");
            try {
                int choice = Scanner.nextInt();
                switch (choice) {
                case 1:
                    s = Scanner.next();
                    if (isValidS(s)) {
                        trie.insert(s);
                    } else {
                        System.out.println("Invalid string: allowed only a-z");
                    }
                    break;
                case 2:
                    s = Scanner.next();
                    if (isValidS(s)) {
                        if (trie.delete(s)) {
                            System.out.println("Word successfully deleted.");
                        } else {
                            System.out.println("Word not found.");
                        }
                    } else {
                        System.out.println("Invalid string: allowed only a-z.");
                    }
                    break;
                case 3:
                    s = Scanner.next();
                    if (isValidS(s)) {
                        if (trie.search(s)) {
                            System.out.println("Word found.");
                        } else {
                            System.out.println("Word not found.");
                        }
                    } else {
                        System.out.println("Invalid string: allowed only a-z.");
                    }
                    break;
                case 4:
                    Scanner.close();
                    System.exit(1);
                    break;
                }
            } catch (Exception e) {
                String badInput = Scanner.next();
                System.out.println("This is bad input: " + badInput);
            }
        }
    }
}