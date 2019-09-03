package datastructure.graph;

public class AdjacencyMatrix {

    private int vertexNum;
    @SuppressWarnings("unused")
    private int edgeNum;
    private int[][] matrix;
    static final int TRUE = 1;
    static final int FALSE = 0;

    public AdjacencyMatrix(int vertexNum) {
        this.vertexNum = vertexNum;
        this.edgeNum = 0;
        this.matrix = new int[vertexNum][vertexNum];
        for (int i = 0; i < vertexNum; i++) {
            for (int j = 0; j < vertexNum; j++) {
                this.matrix[i][j] = FALSE;
            }
        }
    }

    public boolean addEdge(int from, int to) {
        if (from >= 0 && from < vertexNum && to >= 0 && to < vertexNum) {
            if (matrix[from][to] != TRUE) {
                matrix[from][to] = TRUE;
                matrix[to][from] = TRUE;
                edgeNum++;
                return true;
            }
        }
        return false;
    }

    public boolean removeEdge(int from, int to) {
        if (from >= 0 && from < vertexNum && to >= 0 && to < vertexNum) {
            if (matrix[from][to] == TRUE) {
                matrix[from][to] = FALSE;
                matrix[to][from] = FALSE;
                edgeNum--;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("    ");
        for (int i = 0; i < vertexNum; i++) {
            stringBuilder.append(i);
            stringBuilder.append(" ");
        }
        stringBuilder.append("\n");

        for (int i = 0; i < vertexNum; i++) {
            stringBuilder.append(i);
            stringBuilder.append(" : ");
            for (int j = 0; j < vertexNum; j++) {
                stringBuilder.append(matrix[i][j]);
                stringBuilder.append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        AdjacencyMatrix adjacencyMatrix = new AdjacencyMatrix(6);
        adjacencyMatrix.addEdge(1, 2);
        adjacencyMatrix.addEdge(1, 5);
        adjacencyMatrix.addEdge(2, 5);
        adjacencyMatrix.addEdge(1, 2);
        adjacencyMatrix.addEdge(2, 3);
        adjacencyMatrix.addEdge(3, 4);
        adjacencyMatrix.addEdge(4, 1);
        adjacencyMatrix.addEdge(2, 3);
        System.out.println(adjacencyMatrix);
    }
}