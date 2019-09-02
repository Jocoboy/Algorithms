package datastructure.graph;

import java.util.ArrayList;

public class AdjacencyList<T extends Comparable<T>> {

    class Node {

        T data;
        ArrayList<Node> adjacentNodes;

        public Node(T data) {
            adjacentNodes = new ArrayList<>();
            this.data = data;
        }

        /**
         * 
         * @param v - adjacent node
         * @return true if successfully added
         */
        public boolean addAdjacentNode(Node v) {
            for (Node u : adjacentNodes) {
                if (u.data.compareTo(v.data) == 0) {
                    return false;
                }
            }
            return adjacentNodes.add(v);
        }

        /**
         * 
         * @param to - data of adjacent node
         * @return true if successfully removed
         */
        public boolean removeAdjacentNode(T to) {
            for (int i = 0; i < adjacentNodes.size(); i++) {
                if (adjacentNodes.get(i).data.compareTo(to) == 0) {
                    adjacentNodes.remove(i);
                    return true;
                }
            }
            return false;
        }
    }

    private ArrayList<Node> heads;

    public AdjacencyList() {
        heads = new ArrayList<>();
    }

    /**
     * 
     * @param from - data of node is from
     * @param to   - data of node is going to
     * @return true if successfully added
     */
    public boolean addEdge(T from, T to) {
        Node u = null, v = null;
        for (Node n : heads) {
            if (n.data.compareTo(from) == 0) {
                u = n;
            } else if (n.data.compareTo(to) == 0) {
                v = n;
            }
            if (u != null && v != null) {
                break;
            }
        }
        if (u == null) {
            u = new Node(from);
            heads.add(u);
        }
        if (v == null) {
            v = new Node(to);
            heads.add(v);
        }
        return u.addAdjacentNode(v);
    }

    /**
     * 
     * @param from - data of node is from
     * @param to   - data of node is going to
     * @return true if successfully removed
     */
    public boolean removeEdge(T from, T to) {
        Node u = null;
        for (Node n : heads) {
            if (n.data.compareTo(from) == 0) {
                u = n;
                break;
            }
        }
        if (u == null) {
            return false;
        }
        return u.removeAdjacentNode(to);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node n : heads) {
            stringBuilder.append("Vertex: ");
            stringBuilder.append(n.data);
            stringBuilder.append("\n");
            stringBuilder.append("Adjacent verticies: ");
            for (Node n2 : n.adjacentNodes) {
                stringBuilder.append(n2.data);
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        AdjacencyList<Integer> adjacencyList = new AdjacencyList<>();
        adjacencyList.addEdge(1, 2);
        adjacencyList.addEdge(1, 5);
        adjacencyList.addEdge(2, 5);
        adjacencyList.addEdge(1, 2);
        adjacencyList.addEdge(2, 3);
        adjacencyList.addEdge(3, 4);
        adjacencyList.addEdge(4, 1);
        adjacencyList.addEdge(2, 3);
        System.out.println(adjacencyList);
    }
}