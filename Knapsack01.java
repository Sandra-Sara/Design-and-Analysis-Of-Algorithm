import java.util.*;
public class Knapsack01{
      public static int knapsack(int[] benefit,int[] weights,int capacity){
            int n = weights.length;
            int dp[][] = new int[n+1][capacity+1];
            for(int i = 0;i <=n;i++){
                  for(int j = 0;j <= capacity;j++){
                        if(i == 0 || j == 0) dp[i][j] = 0;
                        else if(weights[i-1] <= j){
                              dp[i][j] = Math.max(benefit[i-1]+dp[i-1][j-weights[i-1]],dp[i-1][j]);
                        }
                        else{
                              dp[i][j] = dp[i-1][j];
                        }
                  }
            }
            return dp[n][capacity];
      }
      public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            int N = sc.nextInt();
            int[] weights = new int[N];
            int[] benefit = new int[N];
            for(int i = 0;i < N;i++){
            weights[i] = sc.nextInt();
            }
            for(int i = 0;i < N;i++){
            benefit[i] = sc.nextInt();
            }
            int capacity = sc.nextInt();
            int profit = knapsack(benefit,weights,capacity);
            System.out.println(profit);
      }          
}