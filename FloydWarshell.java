import java.util.Scanner;
public class FloydWarshell {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();  // Input number of vertices
        int[][] graph = new int[n][n];
        System.out.println("Enter the graph as an adjacency matrix (use -1 for no edge): ");
        for (int i = 0; i < n; i++) { // Input graph as an adjacency matrix
            for (int j = 0; j < n; j++) {
                int value = scanner.nextInt();
                graph[i][j] = (value == -1) ? Integer.MAX_VALUE : value;  // Convert -1 to INF
            }
        }
        int[][] dist = new int[n][n]; // Initialize the distance matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    dist[i][j] = 0;  // Distance to self is zero
                } else {
                    dist[i][j] = (graph[i][j] == Integer.MAX_VALUE) ? Integer.MAX_VALUE : graph[i][j];
                }
            }
        }
        for (int k = 0; k < n; k++) {// Floyd-Warshall algorithm
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
                        dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        System.out.println("Shortest distances between all pairs of vertices:");// Print shortest distances
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF ");  // No path exists
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
