import java.util.*;
public class PrimMST{
    static class Edge {
        int node, weight, parent; // parent → কে connect করে আসছে
        Edge(int node, int weight, int parent) { 
            this.node = node;//node → কোন vertex
            this.weight = weight;//weight → edge weight
            this.parent = parent;//parent → কোন vertex থেকে আসছে
        }
    }
    public static int primMST(List<List<Edge>> adjList, int V) {
        boolean[] visited = new boolean[V];
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        List<Edge> mstEdges = new ArrayList<>();
        pq.offer(new Edge(0, 0, -1));  // Start from node 0 (no parent)
        int totalCost = 0;
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if (visited[current.node]) continue;
            visited[current.node] = true;
            totalCost += current.weight;
            if (current.parent != -1) { // Skip the first edge where parent == -1 (starting point)
                mstEdges.add(current);
            }
            for (Edge neighbor : adjList.get(current.node)) {
                if (!visited[neighbor.node]) {
                    pq.offer(new Edge(neighbor.node, neighbor.weight, current.node));
                }
            }
        }
        System.out.println("Edges in MST:");//Print all edges in MST
        for (Edge e : mstEdges) {
            System.out.println(e.parent + " - " + e.node + " : " + e.weight);
        }
        return totalCost;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        List<List<Edge>> adjList = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }
        System.out.println("Enter edges in format: u v weight");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            adjList.get(u).add(new Edge(v, w, u));
            adjList.get(v).add(new Edge(u, w, v)); // undirected for both kruskal and prim
        }
        int cost = primMST(adjList, V);
        System.out.println("Total Cost of MST = " + cost);
    }
}
