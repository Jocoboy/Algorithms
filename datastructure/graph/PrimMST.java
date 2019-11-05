package datastructure.graph;

import java.util.ArrayList;
import java.util.Scanner;

public class PrimMST {

    private int vertexNum;
    private int edgeNum;
    private int[][] matrix;
    private ArrayList<Integer> mst;

    public PrimMST() {
        Scanner scanner = new Scanner(System.in);

        this.vertexNum = scanner.nextInt();
        this.edgeNum = scanner.nextInt();
        matrix = new int[vertexNum + 1][vertexNum + 1];
        // Initialize all edges' weight as INFINITE.
        for (int i = 1; i <= vertexNum; i++) {
            for (int j = 1; j <= vertexNum; j++) {
                matrix[i][j] = Integer.MAX_VALUE;
            }
        }
        mst = new ArrayList<Integer>();

        for (int i = 1; i <= edgeNum; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            int w = scanner.nextInt();
            if (matrix[u][v] > w) {
                matrix[u][v] = w;
                matrix[v][u] = w;
            }
        }

        scanner.close();
    }

    public int Prim() {

        boolean[] vis = new boolean[vertexNum + 1];
        int[] dis = new int[vertexNum + 1];

        for (int i = 1; i <= vertexNum; i++) {
            // Set all vertexes to be unvisited.
            vis[i] = false;
            // If vertex i connects with vertex i, set dis[i] as the weight of edge[1][i].
            // Otherwise, initialize dis[i] as INFINITE.
            dis[i] = matrix[1][i];
        }
        // Select vertex 1 to be the starting vertex.
        vis[1] = true;
        dis[1] = Integer.MAX_VALUE;
        mst.add(1);

        int minWeight = 0;
        for (int i = 1; i <= vertexNum; i++) {
            int k = 1;
            // Select the connected vertex of minimum weight as next vertex to be relaxed.
            for (int j = 1; j <= vertexNum; j++) {
                if (!vis[j] && dis[j] < dis[k]) {
                    k = j;
                }
            }
            // That means no operation is left.
            if (k == 1) {
                break;
            }
            vis[k] = true;
            minWeight += dis[k];
            mst.add(k);
            // Relaxation operation : update values of the dis array.
            // If vertex j connects with vertex k and vertex j is unvisited , then relax it.
            for (int j = 1; j <= vertexNum; j++) {
                if (!vis[j] && dis[j] > matrix[k][j]) {
                    dis[j] = matrix[k][j];
                }
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
            1 3 2   1 3 6 4 2 5
            1 4 5
            2 3 5
            2 5 1
            3 4 5
            3 5 6
            3 6 4
            4 6 2
            5 6 6     
         */
        PrimMST MST = new PrimMST();
        System.out.println("Minimum weight : " + MST.Prim());
        System.out.println("MST : ");
        MST.printMST();
    }
}