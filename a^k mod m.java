import java.util.Scanner;

public class a_k_mod_m {
    public static long modExp(long a, long k, long m) {
        if (k == 0) return 1;

        long half = modExp(a, k / 2, m);
        long result = (half * half) % m;

        if (k % 2 == 1) {
            result = (result * a) % m;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a, k, m: ");
        long a = sc.nextLong();
        long k = sc.nextLong();
        long m = sc.nextLong();

        long answer = modExp(a, k, m);
        System.out.println("Result: " + answer);
    }
}
