package datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class DirectedGraph {

    private int vertexNum;
    private List<ArrayList<Integer>> graph;

    private int time;
    private int[] dfn;
    private int[] low;
    private Stack<Integer> stack;
    private boolean[] inStack;

    private int SCCNum;
    private List<ArrayList<Integer>> SCC;

    public DirectedGraph(int vertexNum, List<ArrayList<Integer>> graph) {
        this.vertexNum = vertexNum;
        this.graph = graph;

        dfn = new int[vertexNum];
        low = new int[vertexNum];
        Arrays.fill(dfn, -1);
        Arrays.fill(low, -1);
        stack = new Stack<Integer>();
        inStack = new boolean[vertexNum];

        SCC = new ArrayList<ArrayList<Integer>>();
    }

    public void findSCC() {
        for (int i = 0; i < vertexNum; i++) {
            if (dfn[i] == -1) {
                tarjan(i);
            }
        }

        System.out.println("SCCNum of DirectedGraph: " + SCCNum);
        for (int i = 0; i < SCC.size(); i++) {
            for (int j = 0; j < SCC.get(i).size(); j++) {
                System.out.print(SCC.get(i).get(j) + " ");
            }
            System.out.println(" ");
        }
    }

    public void tarjan(int u) {
        dfn[u] = low[u] = ++time;
        inStack[u] = true;
        stack.push(u);

        for (int i = 0; i < graph.get(u).size(); i++) {
            int v = graph.get(u).get(i);
            if (dfn[v] == -1) {
                tarjan(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (inStack[v]) {
                low[u] = Math.min(low[u], dfn[v]);
            }
        }

        if (low[u] == dfn[u]) {
            SCCNum++;
            ArrayList<Integer> component = new ArrayList<Integer>();
            int v;
            do {
                v = stack.pop();
                inStack[v] = false;
                component.add(v);
            } while (u != v);
            SCC.add(component);
        }
    }
}

public class StronglyConnectedComponent {

    public static void main(String[] args) {
        int vertexNum = 6;
        List<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < vertexNum; i++) {
            graph.add(new ArrayList<Integer>());
        }
        graph.get(0).add(1);
		graph.get(0).add(2);
		graph.get(1).add(3);
		graph.get(2).add(3);
		graph.get(2).add(4);
		graph.get(3).add(0);
		graph.get(3).add(5);
		graph.get(4).add(5);

        DirectedGraph directedGraph = new DirectedGraph(vertexNum, graph);
        directedGraph.findSCC();
    }
}