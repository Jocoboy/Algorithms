package datastructure.graph;
import java.util.Scanner;

public class FloydWarshall {

    private static final int Infinity = 987654321;
    private int vertexNum;
    private int edgeNum;
    private int[][] matrix;

    public FloydWarshall() {
        Scanner scanner = new Scanner(System.in);
        vertexNum = scanner.nextInt();
        edgeNum = scanner.nextInt();
        matrix = new int[vertexNum + 1][vertexNum + 1];
        for (int i = 1; i <= vertexNum; i++) {
            for (int j = 1; j <= vertexNum; j++) {
                if (i == j) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = Infinity;
                }
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

    public void floyd() {
        for (int k = 1; k <= vertexNum; k++) {
            for (int i = 1; i <= vertexNum; i++) {
                for (int j = 1; j <= vertexNum; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
        print();
    }

    private void print() {
        for (int i = 1; i <= vertexNum; i++) {
            for (int j = 1; j <= vertexNum; j++) {
                System.out.printf("%-10s", matrix[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        FloydWarshall graph = new FloydWarshall();
        graph.floyd();
    }
}

/**
Input:      Output:
4 8         0          2          5          4
1 2 2       9          0          3          4
1 3 6       6          8          0          1
1 4 4       5          7          10         0
2 3 3
3 1 7
3 4 1
4 1 5
4 3 12          
*/