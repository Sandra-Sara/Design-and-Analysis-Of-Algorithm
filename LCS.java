import java.util.*;
public class LCS{
    public static String findLCS(String s1,String s2){
        int m = s1.length();
        int n = s2.length();
        int dp[][] = new int[m+1][n+1];
        for(int i = 1;i <=m;i++){
            for(int j = 1;j <=n ;j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        int i = m;
        int j = n;
        StringBuilder lcs = new StringBuilder();
        while(i > 0 && j > 0){
            if(s1.charAt(i-1) == s2.charAt(j-1)){
                lcs.append(s1.charAt(i-1));
                i--;
                j--;
            }
            else if(dp[i-1][j] > dp[i][j-1]){
                i--;
            }
            else{
                j--;
            }
        }
        return lcs.reverse().toString();
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        String s2 = sc.nextLine();
        String result = findLCS(s1,s2);
        System.out.println(result.length());
        System.out.println(result);
    }
}