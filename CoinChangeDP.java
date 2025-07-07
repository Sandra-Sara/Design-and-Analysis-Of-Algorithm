import java.util.*;
public class CoinChangeDP {
    // Function to find the minimum number of coins for a given amount
    public static int coinChange(int[] coins, int amount) {
        // Create an array to store the minimum coins needed for each amount up to the given amount
        int[] dp = new int[amount + 1];

        Arrays.fill(dp, amount + 1);
        dp[0] = 0; // Base case: 0 coins are needed to make amount 0
        // Loop through each coin and update the dp array
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        // If dp[amount] is still the large value, return -1 as it is not possible to make the amount
        if (dp[amount] > amount) {
        return -1;
        }
     else {
        return dp[amount];
         }
    }
    public static void main(String[] args) {
        int[] coins = {1, 2, 5}; // Coin denominations
        int amount = 11; // Target amount
        int result = coinChange(coins, amount);// Calculate the minimum number of coins needed
        if (result == -1) {
            System.out.println("It is not possible to make the target amount with the given coins.");
        } else {
            System.out.println("The minimum number of coins required: " + result);
        }
    }
}
