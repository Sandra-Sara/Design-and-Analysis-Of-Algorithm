import java.util.*;
public class SubsetSum {
    public static boolean isSubsetSum(int[] arr, int N, int sum) {
        boolean[][] dp = new boolean[N + 1][sum + 1];
        // If sum is 0, then answer is true, as we can always get sum 0 with no elements
        for (int i = 0; i <= N; i++) {
            dp[i][0] = true;
        }
        // Fill the dp table
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= sum; j++) {
                // If arr[i-1] is greater than sum, we cannot include it in the subset
                if (arr[i - 1] <= j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - arr[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        // The bottom right corner of the dp table contains the result
        return dp[N][sum];
    }
    public static void main(String[] args) {
        // Example Input
        int[] arr = {2, 5, 8, 12, 6, 14}; // Array of numbers
        int N = arr.length; // Length of array
        int sum = 19; // Desired sum
        // Check if a subset with the given sum exists
        if (isSubsetSum(arr, N, sum)) {
            System.out.println("There exists a subset with the sum " + sum);
        } else {
            System.out.println("No subset with the sum " + sum);
        }
    }
}
