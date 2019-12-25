package datastructure.graph;
import java.util.ArrayList;
import java.util.Scanner;

public class BellmanFord {

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

    public BellmanFord() {
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

    public void bellman() {
        // Relaxation operations for n-1 times.
        int[] dis = new int[vertexNum + 1];
        for (int i = 1; i <= vertexNum; i++) {
            dis[i] = Infinity;
        }

        for (int i = 1; i <= vertexNum - 1; i++) {
            for (edge e : edges) {
                dis[e.to] = Math.min(dis[e.to], dis[e.from] + e.weight);
            }
        }

        for (edge e : edges) {
            if (dis[e.to] > dis[e.from] + e.weight) {
                System.out.println("Negative weight circle exists.");
                return;
            }
        } 
        System.out.println("Negative weight circle is non-exist.");
    }

    public static void main(String[] args) {

        BellmanFord graph = new BellmanFord();
        graph.bellman();
    }
}

/**
Input:      Output:
3 4         Negative weight circle is non-exist.
1 2 2       --------------------------------
1 3 4       Negative weight circle exists.
2 3 1       
3 1 -3
--------
3 3
1 2 3
2 3 4
3 1 -8
*/