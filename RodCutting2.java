public class RodCutting2 {
    public static int rodCutting(int[] price, int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i <= j) {
                    dp[i][j] = Math.max(dp[i - 1][j], price[i - 1] + dp[i][j - i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][n]; // Maximum profit for a rod of length n
    }
    public static void main(String[] args) {
        int[] prices = {2, 5,7,3,9};
        int n = prices.length; 
        int maxProfit = rodCutting(prices, n);
        System.out.println("Maximum revenue that can be obtained: " + maxProfit);
    }
}

