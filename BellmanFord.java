import java.util.*;
class BellmanFord{
    public static boolean bellmanFord(int V, int E, int[][] edges, int[] weights, int source){
        int[] dist = new int[V];//Initialize all distances from source
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        for (int i = 1; i < V - 1; i++){ //Relax all edges |V| - 1 times
            for (int j = 0; j < E; j++){ //Relax each edge (u, v) ∈ E
                int u = edges[j][0];
                int v = edges[j][1];
                int w = weights[j];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]){// Relaxation step
                    dist[v] = dist[u] + w;
                }
            }
        }
        for (int i = 0; i < E; i++){ //Check for negative-weight cycles
            int u = edges[i][0];
            int v = edges[i][1];
            int w = weights[i];
            if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]){
                // If we can still relax an edge, then there's a negative-weight cycle
                return false;
            }
        }
        System.out.println("Shortest distances from source vertex " + source + ":");
        for (int i = 0; i < V; i++){
            if (dist[i] == Integer.MAX_VALUE){
                System.out.println("Vertex " + i + " is unreachable.");
            } else{
                System.out.println("Distance from " + source + " to " + i + " is " + dist[i]);
            }
        }
        // Step 6: Return true indicating no negative-weight cycle
        return true;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        // Defining arrays to store the edges and their weights
        int[][] edges = new int[E][2]; // 2 for (u, v)
        int[] weights = new int[E];
        System.out.println("Enter the edges (u v) and the weight of each edge:");
        for (int i = 0; i < E; i++){
            System.out.print("Edge " + (i + 1) + ": ");
            edges[i][0] = sc.nextInt(); // u
            edges[i][1] = sc.nextInt(); // v
            weights[i] = sc.nextInt();  // weight
        }
        int source = sc.nextInt();
        boolean result = bellmanFord(V, E, edges, weights, source);
        if (result){
            System.out.println("No negative-weight cycle detected.");
        } else{
            System.out.println("Negative-weight cycle detected.");
        }
    }
}
