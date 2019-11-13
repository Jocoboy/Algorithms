package datastructure.graph;

import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;

public class CriticalPath {

    class edge {
        int from;
        int to;
        int next;
        int weight;
    }

    private int vertexNum;
    private int edgeNum;
    private edge[] edges;
    private int[] head;
    private int[] inDegree;
    private Stack<Integer> topo;

    private int[] VE;
    private int[] VL;
    private int[] E;
    private int[] L;

    public CriticalPath() {
        Scanner scanner = new Scanner(System.in);
        vertexNum = scanner.nextInt();
        edgeNum = scanner.nextInt();
        edges = new edge[edgeNum + 1];
        head = new int[vertexNum + 1];
        inDegree = new int[vertexNum + 1];
        topo = new Stack<Integer>();

        VE = new int[vertexNum + 1];
        VL = new int[vertexNum + 1];
        E = new int[edgeNum + 1];
        L = new int[edgeNum + 1];
        for (int i = 0; i <= vertexNum; i++) {
            head[i] = -1;
        }

        for (int i = 1; i <= edgeNum; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();
            inDegree[to]++;
            edges[i] = new edge();
            edges[i].from = from;
            edges[i].to = to;
            edges[i].next = head[from];
            head[from] = i;
            edges[i].weight = weight;
        }

        scanner.close();
    }

    public void topoSort() {
        Queue<Integer> que = new LinkedList<Integer>();
        for (int i = 1; i <= vertexNum; i++) {
            if (inDegree[i] == 0) {
                que.add(i);
            }
        }
        while (!que.isEmpty()) {
            int from = que.poll();
            topo.push(from);
            for (int i = head[from]; i != -1; i = edges[i].next) {
                int to = edges[i].to;
                int weight = edges[i].weight;
                if (--inDegree[to] == 0) {
                    que.add(to);
                }
                VE[to] = Math.max(VE[to], VE[from] + weight);
            }
        }
    }

    public void solve() {
        topoSort();
        System.out.println(VE[vertexNum]);
        for (int i = 1; i <= vertexNum; i++) {
            VL[i] = VE[vertexNum];
        }
        while (!topo.isEmpty()) {
            int from = topo.pop();
            for (int i = head[from]; i != -1; i = edges[i].next) {
                int to = edges[i].to;
                int weight = edges[i].weight;
                VL[from] = Math.min(VL[from], VL[to] - weight);
            }
        }
        for (int from = 1; from <= vertexNum; from++) {
            for (int i = head[from]; i != -1; i = edges[i].next) {
                int to = edges[i].to;
                int weight = edges[i].weight;
                E[i] = VE[from];
                L[i] = VL[to] - weight;
                if (E[i] == L[i]) {
                    System.out.println(from + " " + to);
                }
            }
        }
    }

    public static void main(String[] args) {

        CriticalPath cp = new CriticalPath();
        cp.solve();
    }
}