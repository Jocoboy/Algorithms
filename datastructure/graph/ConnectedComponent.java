package datastructure.graph;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

class UndirectedGraph<T extends Comparable<T>> {

    class Node {
        T data;

        public Node(T data) {
            this.data = data;
        }
    }

    class Edge {
        Node u, v;

        public Edge(Node u, Node v) {
            this.u = u;
            this.v = v;
        }
    }

    ArrayList<Node> nodeList;
    ArrayList<Edge> edgeList;

    int CCNum;
    private List<ArrayList<Node>> CC;

    public UndirectedGraph() {
        nodeList = new ArrayList<Node>();
        edgeList = new ArrayList<Edge>();
        CC = new ArrayList<ArrayList<Node>>();
    }

    public void addEdge(T from, T to) {
        Node u = null, v = null;
        for (Node n : nodeList) {
            if (n.data.compareTo(from) == 0) {
                u = n;
            } else if (n.data.compareTo(to) == 0) {
                v = n;
            }
        }
        if (u == null) {
            u = new Node(from);
            nodeList.add(u);
        }
        if (v == null) {
            v = new Node(to);
            nodeList.add(v);
        }
        edgeList.add(new Edge(u, v));
    }

    public void findCC() {
        Set<Node> visNodes = new HashSet<Node>();
        for (Node n : nodeList) {
            if (!visNodes.contains(n)) {
                visNodes.add(n);
                ArrayList<Node> component = getComponent(n, new ArrayList<Node>());
                visNodes.addAll(component);
                CC.add(component);
                CCNum++;
            }
        }

        System.out.println("CCNum of UndirectedGraph: " + CCNum);
        for (int i = 0; i < CC.size(); i++) {
            for (int j = 0; j < CC.get(i).size(); j++) {
                System.out.print(CC.get(i).get(j).data + " ");
            }
            System.out.println(" ");
        }
    
    }

    public ArrayList<Node> getComponent(Node n, ArrayList<Node> component) {
        component.add(n);
        for (Edge e : edgeList) {
            if (e.u.equals(n) && !component.contains(e.v)) {
                getComponent(e.v, component);
            }
        }
        return component;
    }
}

public class ConnectedComponent {

    public static void main(String[] args) {
        UndirectedGraph<Character> undirectedGraph1 = new UndirectedGraph<Character>();
        undirectedGraph1.addEdge('a', 'b');
        undirectedGraph1.addEdge('a', 'e');
        undirectedGraph1.addEdge('b', 'e');
        undirectedGraph1.addEdge('b', 'c');
        undirectedGraph1.addEdge('c', 'd');
        undirectedGraph1.addEdge('d', 'a');

        undirectedGraph1.addEdge('x', 'y');
        undirectedGraph1.addEdge('x', 'z');

        undirectedGraph1.addEdge('w', 'w');
        undirectedGraph1.findCC();

        UndirectedGraph<Integer> undirectedGraph2 = new UndirectedGraph<Integer>();
        undirectedGraph2.addEdge(1, 2);
        undirectedGraph2.addEdge(2, 3);
        undirectedGraph2.addEdge(2, 4);
        undirectedGraph2.addEdge(3, 5);

        undirectedGraph2.addEdge(7, 8);
        undirectedGraph2.addEdge(8, 10);
        undirectedGraph2.addEdge(10, 8);
        undirectedGraph2.findCC();
    }
}