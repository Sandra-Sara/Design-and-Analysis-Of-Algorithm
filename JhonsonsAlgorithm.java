import java.util.*;
public class JohnsonAlgorithm {
    // Bellman-Ford algorithm to detect negative-weight cycles
    public static boolean bellmanFord(int[][] graph, int[] dist, int source) {
        int V = graph.length;
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        // Relax all edges (V-1) times
        for (int i = 1; i < V - 1; i++) {
            for (int u = 0; u < V; u++) {
                for (int v = 0; v < V; v++) {
                    if (graph[u][v] != Integer.MAX_VALUE && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
        }
        // Check for negative-weight cycles
        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != Integer.MAX_VALUE && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    return false; // Negative cycle detected
                }
            }
        }
        return true; // No negative cycle
    }
    // Dijkstra algorithm to find shortest paths
    public static int[] dijkstra(int[][] graph, int source) {
        int V = graph.length;
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{source, 0});
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int u = current[0];
            int d = current[1];
            if (d > dist[u]) continue;
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }
        return dist;
    }
    public static int[][] johnson(int[][] graph) {
        int V = graph.length;
        int[][] newGraph = new int[V + 1][V + 1];
        // Step 1: Add a new vertex s and edges with 0 weight to all other vertices
        for (int i = 0; i < V; i++) {
            newGraph[i][V] = 0;
            newGraph[V][i] = Integer.MAX_VALUE;
        }
        newGraph[V][V] = 0;
        // Step 2: Run Bellman-Ford on the new graph
        int[] h = new int[V + 1];
        if (!bellmanFord(newGraph, h, V)) {
            System.out.println("The graph contains a negative-weight cycle");
            return null; // Negative cycle detected
        }
        // Step 3: Re-weight the edges
        int[][] reweightedGraph = new int[V][V];
        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != Integer.MAX_VALUE) {
                    reweightedGraph[u][v] = graph[u][v] + h[u] - h[v];
                } else {
                    reweightedGraph[u][v] = Integer.MAX_VALUE;
                }
            }
        }
        // Step 4: Run Dijkstra's algorithm for each vertex
        int[][] result = new int[V][V];
        for (int u = 0; u < V; u++) {
            int[] dist = dijkstra(reweightedGraph, u);
            for (int v = 0; v < V; v++) {
                if (dist[v] != Integer.MAX_VALUE) {
                    result[u][v] = dist[v] - h[u] + h[v];
                } else {
                    result[u][v] = Integer.MAX_VALUE;
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Take the number of vertices as input
        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        // Create the graph adjacency matrix
        int[][] graph = new int[V][V];
        // Taking input for the graph adjacency matrix
        System.out.println("Enter the adjacency matrix (use -1 for no edge, and positive integers for edge weights):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                int weight = scanner.nextInt();
                if (weight == -1) {
                    graph[i][j] = Integer.MAX_VALUE;  // No edge between i and j
                } else {
                    graph[i][j] = weight;  // Edge weight
                }
            }
        }
        // Call the Johnson's algorithm
        int[][] shortestPaths = johnson(graph);
        // Print the shortest path matrix
        if (shortestPaths != null) {
            System.out.println("Shortest path matrix (using Johnson's algorithm):");
            for (int i = 0; i < shortestPaths.length; i++) {
                for (int j = 0; j < shortestPaths[i].length; j++) {
                    if (shortestPaths[i][j] == Integer.MAX_VALUE) {
                        System.out.print("INF ");
                    } else {
                        System.out.print(shortestPaths[i][j] + " ");
                    }
                }
                System.out.println();
            }
        }
        scanner.close();
    }
}
