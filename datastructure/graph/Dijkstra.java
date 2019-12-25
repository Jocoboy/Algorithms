package datastructure.graph;
import java.util.Scanner;

public class Dijkstra {

    private static final int Infinity = 987654321;
    private int vertexNum;
    private int edgeNum;
    private int[][] matrix;

    public Dijkstra() {
        Scanner scanner = new Scanner(System.in);
        vertexNum = scanner.nextInt();
        edgeNum = scanner.nextInt();
        matrix = new int[vertexNum + 1][vertexNum + 1];
        for (int i = 1; i <= vertexNum; i++) {
            for (int j = 1; j <= vertexNum; j++) {
                matrix[i][j] = Infinity;
            }
        }
        for (int i = 1; i <= edgeNum; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();
            matrix[from][to] = weight;
        }
        scanner.close();
    }

    public int dijkstra() {
        boolean[] vis = new boolean[vertexNum + 1];
        int[] dis = new int[vertexNum + 1];

        for (int i = 1; i <= vertexNum; i++) {
            vis[i] = false;
            dis[i] = matrix[1][i];
        }
        vis[1] = true;
        dis[1] = 0;

        for (int i = 2; i <= vertexNum; i++) {
            int k = 0;
            int min_dis = Infinity;
            for (int j = 1; j <= vertexNum; j++) {
                if (!vis[j] && dis[j] < min_dis) {
                    min_dis = dis[j];
                    k = j;
                }
            }
            if (min_dis == Infinity) {
                break;
            }
            vis[k] = true;
            for (int j = 1; j <= vertexNum; j++) {
                if (!vis[j] && dis[j] > dis[k] + matrix[k][j]) {
                    dis[j] = dis[k] + matrix[k][j];
                }
            }
        }
        return dis[vertexNum];
    }

    public static void main(String[] args) {
        Dijkstra graph = new Dijkstra();
        System.out.println("Length of shortest path: " + graph.dijkstra());
    }
}
/**
Input:      Output:
6 9         Length of shortest path: 17
1 2 1
1 3 12
2 3 9
2 4 3
3 5 5
4 3 4
4 5 13
4 6 15
5 6 4
*/