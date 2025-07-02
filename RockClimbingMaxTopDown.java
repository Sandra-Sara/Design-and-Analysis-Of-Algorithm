import java.util.*;
public class RockClimbingMaxTopDown {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        int[][] wall = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                wall[i][j] = sc.nextInt();
            }
        }
        int[][] dp = new int[row][col];
        for (int j = 0; j < col; j++) {
            dp[0][j] = wall[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int left, up, right;
                if (j > 0) 
                left = dp[i - 1][j - 1];
                else 
                left = -100000000;
               up = dp[i - 1][j];
                if (j < col - 1) 
                right = dp[i - 1][j + 1];
                else 
                right = -100000000;
        int max = Math.max(up, Math.max(left, right));
                dp[i][j] = wall[i][j] + max;
            }
        }
        int maxDanger = dp[row - 1][0];
        for (int j = 1; j < col; j++) {
            if (dp[row - 1][j] > maxDanger) {
                maxDanger = dp[row - 1][j];
            }
        }
        System.out.println(maxDanger);
    }
}
