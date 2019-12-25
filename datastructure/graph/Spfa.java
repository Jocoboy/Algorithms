package datastructure.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Spfa {

    class edge {
        int from;
        int to;
        int weight;

        public edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static final int Infinity = 987654321;
    private int vertexNum;
    private int edgeNum;
    private ArrayList<edge> edges;

    public Spfa() {
        Scanner scanner = new Scanner(System.in);
        vertexNum = scanner.nextInt();
        edgeNum = scanner.nextInt();
        edges = new ArrayList<edge>();
        for (int i = 1; i <= edgeNum; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();
            edges.add(new edge(from, to, weight));
        }
        scanner.close();
    }

    public void spfa(int s) {
        int[] dis = new int[vertexNum + 1];
        boolean[] inQueue = new boolean[vertexNum + 1];
        int[] inQueueTimes = new int[vertexNum + 1];
        for (int i = 1; i <= vertexNum; i++) {
            dis[i] = Infinity;
        }

        dis[s] = 0;
        inQueue[s] = true;
        inQueueTimes[s]++;
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(s);

        while (!que.isEmpty()) {
            int from = que.poll();
            inQueue[from] = false;

            for (edge e : edges) {
                if (from == e.from && dis[e.to] > dis[e.from] + e.weight) {
                    dis[e.to] = dis[e.from] + e.weight;
                    if (!inQueue[e.to]) {
                        inQueue[e.to] = true;
                        inQueueTimes[e.to]++;
                        que.add(e.to);
                        if (inQueueTimes[e.to] > vertexNum) {
                            System.out.println("Negative weight circle exists.");
                            return;
                        }
                    }
                }
            }
        }
        System.out.println("Negative weight circle is non-exist.");
        System.out.println("Length of Shortest Path: " + dis[vertexNum]);
    }

    public static void main(String[] args) {
        Spfa graph = new Spfa();
        graph.spfa(1);
    }
}

/**
Input:      Output:
3 4         Negative weight circle is non-exist.
1 2 2       Length of Shortest Path: 3
1 3 4       --------------------------------
2 3 1       Negative weight circle exists.
3 1 -3       
--------
3 3
1 2 3
2 3 4
3 1 -8
*/