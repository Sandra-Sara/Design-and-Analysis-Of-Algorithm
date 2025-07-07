import java.util.*;
public class SCC {
    static int vertices;
    static ArrayList<List<Integer>> adjList = new ArrayList<>();
    static ArrayList<List<Integer>> transpose = new ArrayList<>();
    public static void dfs1(int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : adjList.get(node)) {
            if (!visited[neighbor]) {
                dfs1(neighbor, visited, stack);
            }
        }
        stack.push(node);
    }
    public static void dfs2(int node, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);
        for (int neighbor : transpose.get(node)) {
            if (!visited[neighbor]) {
                dfs2(neighbor, visited, component);
            }
        }
    }
    public static void addEdge(int u, int v) {
        adjList.get(u).add(v);
    }
    public static void findSCCs() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[vertices];
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs1(i, visited, stack);
            }
        }
        for (int i = 0; i < vertices; i++) {
            for (int neighbor : adjList.get(i)) {
                transpose.get(neighbor).add(i);
            }
        }
        Arrays.fill(visited, false);
        System.out.println("Strongly Connected Components:");
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                List<Integer> component = new ArrayList<>();
                dfs2(node, visited, component);
                System.out.println(component);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        vertices = sc.nextInt();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
            transpose.add(new ArrayList<>());
        }
        int e = sc.nextInt();
        for (int i = 0; i < e; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(u, v);
        }
        findSCCs();
    }
}
