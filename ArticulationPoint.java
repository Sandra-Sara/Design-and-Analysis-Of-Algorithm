import java.util.*;
public class ArticulationPoint{
      static boolean[] visited;
      static int[] disc, low, parent;
      static int time = 0;
      static ArrayList<List<Integer>> adjList = new ArrayList<>();
      static boolean[] ap;
      static int vertices;
    public static void addEdge(int u, int v){
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }
    public static void dfs(int u){
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;
        for (int v : adjList.get(u)) {
            if (!visited[v]) {
                children++;
                parent[v] = u;
                dfs(v);
                low[u] = Math.min(low[u], low[v]);
                if (parent[u] == -1 && children > 1){
                    ap[u] = true;
                }
                if (parent[u] != -1 && low[v] >= disc[u]){
                    ap[u] = true;
                }
            }
            else if (v != parent[u]){
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
    public static void findArticulationPoints() {
        visited = new boolean[vertices];
        disc = new int[vertices];
        low = new int[vertices];
        parent = new int[vertices];
        ap = new boolean[vertices];
        Arrays.fill(parent, -1);

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
        System.out.println("Articulation Points:");
        for (int i = 0; i < vertices; i++) {
            if (ap[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        vertices = sc.nextInt();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
        int edges = sc.nextInt();
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(u, v);
        }
        findArticulationPoints();
    }
}
