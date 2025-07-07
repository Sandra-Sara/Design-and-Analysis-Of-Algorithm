
import java.util.*;
public class TopologicalSort{
      static int vertices;
      static  ArrayList<List<Integer>> adjList = new ArrayList<>();
      public static void dfs(int vertice,boolean[] visited,Stack<Integer> stack)
      {
             visited[vertice] = true;
            for(int neighbor : adjList.get(vertice)){
                  if(!visited[neighbor]){
                  dfs(neighbor,visited,stack);
                  }
                  
            }
            stack.push(vertice);
      }
 public static void topologicalSort() {
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfs(i, visited, stack);
            }
        }
        System.out.println("Topological Sort order:");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
    public static void addEdge(int u,int v){
          adjList.get(u).add(v);
    }
public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      vertices = sc.nextInt();
      for(int i = 0;i < vertices;i++){
            adjList.add(new ArrayList<>());
      }
      int eNum = sc.nextInt();
      for(int i = 0;i < eNum;i++){
            int u = sc.nextInt();
            int v = sc.nextInt();
            addEdge(u,v);
      }
      topologicalSort();
      
}
}