import java.util.*;
public class Dijkstra {
    static class Edge {
        int v, weight;
        Edge(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }
    public static void dijkstra(List<List<Edge>> graph, int V, int start) {
        int[] dist = new int[V];// Distance array initialized with infinity
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;//Starting node has distance 0
        boolean[] visited = new boolean[V];//Visited array to keep track of processed nodes
        // Priority Queue to always process the node with the smallest distance
        PriorityQueue<Integer> pq = new PriorityQueue<>(
            (v1, v2) -> dist[v1] - dist[v2]  // Compare based on dist[] values
        );
        pq.add(start);  // Start from the source node
        while (!pq.isEmpty()) {
            int u = pq.poll();// Get the node with the smallest distance
            if (visited[u]) continue;//Skip if the node has already been visited
            visited[u] = true;// Mark the node as visited
            // Relax the edges of the current node
            for (Edge neighbor : graph.get(u)) {//graph.get(u) হচ্ছে node u এর সব adjacent edges এর লিস্ট। 
                //neighbor হচ্ছে এর মধ্যে একেকটা Edge object যা v (destination node) এবং 
                //weight (edge weight) ধারণ করে।
                int v = neighbor.v;
                int weight = neighbor.weight;
                // If the path to 'v' through 'u' is shorter, update it
                if (!visited[v] && dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight;
                    pq.add(v);  // Add the neighbor to the queue to process it later
                }
            }
        }
        System.out.println("Shortest distances from node " + start + ":");
        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println("Node " + i + ": No path");
            } else {
                System.out.println("Node " + i + ": " + dist[i]);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            graph.add(new ArrayList<>());
        }
        System.out.println("Enter edges in format: u v weight");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int weight = sc.nextInt();
            graph.get(u).add(new Edge(v, weight));  // Directed edge
            graph.get(v).add(new Edge(u, weight));  // For undirected graph
        }
        System.out.print("Enter the start node: ");
        int start = sc.nextInt();
        dijkstra(graph, V, start);
    }
}
