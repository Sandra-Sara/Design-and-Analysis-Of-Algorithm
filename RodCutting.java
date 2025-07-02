public class RodCutting{
    public static int rodCut(int[] prize, int n){
      int[] dp = new int[n + 1];
        dp[0] = 0; 
        for (int i=1;i<=n;i++) {
            int maximumPrice = -199999;
            for (int j = 1; j <=i;j++){
             maximumPrice = Math.max(maximumPrice, prize[j - 1] + dp[i-j]);
            }
            dp[i] = maximumPrice;
        }
        return dp[n];
    }
    public static void main(String[] args) {
      int[] prices = {1, 5, 8, 9, 10, 17, 20, 24, 30, 6};
     int n = 10;
        int maxProfit = rodCut(prices,n);
          System.out.println(maxProfit);
    }
}