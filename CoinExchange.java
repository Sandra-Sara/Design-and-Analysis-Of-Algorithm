import java.util.*;
public class CoinExchange{
    public static int mincoinExchange(Integer[] coins,int value){
         Arrays.sort(coins,Collections.reverseOrder());
         int count = 0;
         for(int i = 0;i < coins.length;i++){
            int used = value/coins[i];
            count = count + used;
            value = value%coins[i]; 
         }
         return count;
        }   
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Integer[] coins = new Integer[n];
        for(int i = 0;i < n;i++){
            coins[i] = sc.nextInt();
        }
        int value = sc.nextInt();
        int result = mincoinExchange(coins, value);
        System.out.println(result);
    }
}