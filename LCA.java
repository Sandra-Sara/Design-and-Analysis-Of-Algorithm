import java.util.*;
class Node{
    int value;
    Node left ,right;
    Node(int data){
        value = data;
        left = right = null;
    }
}
public class LCA{
    static Node insertNode(Node root,int data){
        if(root == null){
            return new Node(data);
        }
        if(data < root.value){
            root.left = insertNode(root.left,data);
        }
        else{
            root.right = insertNode(root.right,data);
        }
        return root;
    }
    static Node findLCA(Node root,int a,int b){
        if(root == null){
            return null;
        }
        else if(root.value > a && root.value >b){
            return findLCA(root.left,a,b);
        }
        else if(root.value < a && root.value < b){
            return findLCA(root.right,a,b);
        }
        return root;
        }
           public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node root = null;
        for(int i= 0;i < n;i++){
            int v = sc.nextInt();
            root = insertNode(root,v);
        }
        int a = sc.nextInt();
        int b= sc.nextInt();
        Node lca = findLCA(root,a,b);
        if(lca != null){
            System.out.println(lca.value);
        } else {
            System.out.println("LCA not found.");
        }
        sc.close();
    }
}
