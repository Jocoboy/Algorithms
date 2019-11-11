package datastructure.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class KruskalMST {

    private int vertexNum;
    private int edgeNum;
    private int[] father;
    private ArrayList<edge> edges;
    private ArrayList<Integer> mst;

    class edge {
        int from;
        int to;
        int weight;

        edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    KruskalMST() {
        Scanner scanner = new Scanner(System.in);

        this.vertexNum = scanner.nextInt();
        this.edgeNum = scanner.nextInt();
        father = new int[vertexNum + 1];
        edges = new ArrayList<edge>();
        for (int i = 0; i < edgeNum; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();
            edges.add(new edge(from, to, weight));
        }
        Collections.sort(edges, new Comparator<edge>() {
            @Override
            public int compare(edge e1, edge e2) {
                if (e1.weight > e2.weight) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        mst = new ArrayList<Integer>();

        for (int i = 1; i < vertexNum; i++) {
            father[i] = i;
        }
        scanner.close();
    }

    public int find(int x) {
        // Path compression.
        return (x == father[x]) ? x : (father[x] = find(father[x]));
    }

    public boolean unite(int x, int y) {
        int fx = find(x);
        int fy = find(y);
        if (fx != fy) {
            father[fx] = father[fy];
            return false;
        }
        return true;
    }

    public int Kruskal() {
        int minWeight = 0;
        for (edge e : edges) {
            if (!unite(e.from, e.to)) {
                if (mst.indexOf(e.from) == -1) {
                    mst.add(e.from);
                }
                if (mst.indexOf(e.to) == -1) {
                    mst.add(e.to);
                }
                minWeight += e.weight;
            }
        }
        return minWeight;
    }

    public void printMST() {
        for (Integer e : mst) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
         /**             
         * Input:   Output:
         *  6 10    Minimum weight : 14
            1 2 6   MST :
            1 3 2   2 5 4 6 1 3
            1 4 5
            2 3 5
            2 5 1
            3 4 5
            3 5 6
            3 6 4
            4 6 2
            5 6 6     
         */
        KruskalMST MST = new KruskalMST();
        System.out.println("Minimum weight : " + MST.Kruskal());
        System.out.println("MST : ");
        MST.printMST();
    }
}