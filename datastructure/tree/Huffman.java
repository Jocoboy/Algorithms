package datastructure.tree;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Huffman {

    private class TreeNode {

        Character ch;
        int tag;
        int freq;
        TreeNode left;
        TreeNode right;

        public TreeNode(Character ch, int tag, int freq, TreeNode left, TreeNode right) {
            this.ch = ch;
            this.tag = tag;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode constructTree(String str) {
        // Special Judement.
        if (str == null || str.equals("")) {
            return null;
        }
        // Count char frequency and add to Map.
        Map<Character, Integer> freqMap = new HashMap<Character, Integer>();
        for (int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);
            if (freqMap.containsKey(ch)) {
                freqMap.put(ch, freqMap.get(ch) + 1);
            } else {
                freqMap.put(ch, 1);
            }
        }
        // Initialize nodes and sort them according to their frequency.
        LinkedList<TreeNode> nodeList = new LinkedList<TreeNode>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            nodeList.add(new TreeNode(entry.getKey(), 0, entry.getValue(), null, null));
        }
        Collections.sort(nodeList, new Comparator<TreeNode>() {
            public int compare(TreeNode n1, TreeNode n2) {
                return n1.freq - n2.freq;
            }
        });
        // Special Judement.
        if (nodeList.size() == 1) {
            TreeNode tempNode = nodeList.get(0);
            return new TreeNode(null, 0, tempNode.freq, tempNode, null);
        }
        // Construct binary tree with the sorted nodeList.
        TreeNode rootNode = null;
        while (nodeList.size() > 0) {
            TreeNode n1 = nodeList.removeFirst();
            TreeNode n2 = nodeList.removeFirst();
            n1.tag = 0;
            n2.tag = 1;
            if (nodeList.size() == 0) {
                rootNode = new TreeNode(null, 0, n1.freq + n2.freq, n1, n2);
            } else {
                TreeNode tempNode = new TreeNode(null, 0, n1.freq + n2.freq, n1, n2);
                if (tempNode.freq > nodeList.getLast().freq) {
                    nodeList.addLast(tempNode);
                } else {
                    for (int i = 0; i < nodeList.size(); i++) {
                        if (tempNode.freq <= nodeList.get(i).freq) {
                            nodeList.add(i, tempNode);
                            break;
                        }
                    }
                }
            }
        }
        return rootNode;
    }

    private void findPath(TreeNode rootNode, Map<Character, String> res, StringBuilder path) {
        if (rootNode.left == null || rootNode.right == null) {
            path.append(rootNode.tag);
            res.put(rootNode.ch, path.substring(1));
            path.deleteCharAt(path.length() - 1);
            return;
        }
        path.append(rootNode.tag);
        if (rootNode.left != null) {
            findPath(rootNode.left, res, path);
        }
        if (rootNode.right != null) {
            findPath(rootNode.right, res, path);
        }
        path.deleteCharAt(path.length() - 1);
    }

    public Object[] encode(String str) {
        Object[] res = new Object[2];
        Map<Character, String> encodeMap = new HashMap<Character, String>();
        TreeNode tree = constructTree(str);
        findPath(tree, encodeMap, new StringBuilder());
        findPath(tree, encodeMap, new StringBuilder());
        StringBuilder encodeStr = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            encodeStr.append(encodeMap.get(str.charAt(i)));
        }
        res[0] = encodeStr.toString();
        res[1] = encodeMap;
        return res;
    }

    public String decode(String encodeStr, Map<Character, String> encodeMap) {
        StringBuilder decodeStr = new StringBuilder();
        while (encodeStr.length() > 0) {
            for (Map.Entry<Character, String> entry : encodeMap.entrySet()) {
                if (encodeStr.startsWith(entry.getValue())) {
                    decodeStr.append(entry.getKey());
                    encodeStr = encodeStr.substring(entry.getValue().length());
                    break;
                }
            }
        }
        return decodeStr.toString();
    }

    public static void main(String[] args) {
        Huffman hf = new Huffman();

        String str = "aaabbbeeedacfwwwwddd";
        System.out.println("Before encoding: " + str);

        Object[] encodeRes = hf.encode(str);
        String encodeStr = (String) encodeRes[0];
        Map<Character, String> encodeMap = (Map<Character, String>) encodeRes[1];
        System.out.println("After encoding: " + encodeStr);

        System.out.println("encode table: ");
        for (Map.Entry<Character, String> entry : encodeMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

        String decodeStr = hf.decode(encodeStr, encodeMap);
        System.out.println("After decoding: " + decodeStr);
    }
}