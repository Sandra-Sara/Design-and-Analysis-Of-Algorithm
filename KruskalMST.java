import java.util.*;
public class KruskalMST {
    static class Edge implements Comparable<Edge> {
        int u, v, weight; //u = 1st vertex, v = 2nd vertex, weight = edge weight
        Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.weight = w;
        }
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }
    static int[] parent;
    public static int find(int x) { //Finding parent of x
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }
    public static void union(int x, int y) {// Union operation to connect two components
        int rootX = find(x);
        int rootY = find(y);
        parent[rootX] = rootY;
    }
    public static int kruskalMST(List<Edge> edges, int V) {
        Collections.sort(edges); // sort by weight
        parent = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }
        int totalCost = 0;
        List<Edge> mst = new ArrayList<>();
        for (Edge e : edges) {//ছোট weight এর edge নাও
	                         //যদি cycle না করে (different set) → add করো	                         //set এক করে union করো
	                         //cost যোগ করো
	                         //MST লিস্টে edge রাখো
            if (find(e.u) != find(e.v)) {
                union(e.u, e.v);
                totalCost += e.weight;
                mst.add(e);
            }
        }                     
        System.out.println("Edges in MST:");// Output MST edges
        for (Edge e : mst) {
            System.out.println(e.u + " - " + e.v + " : " + e.weight); //exp 2-3:4
        }
        return totalCost;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            edges.add(new Edge(u, v, w));
        }
        int cost = kruskalMST(edges, V);
        System.out.println("Minimum Cost of MST = " + cost);
    }
}
