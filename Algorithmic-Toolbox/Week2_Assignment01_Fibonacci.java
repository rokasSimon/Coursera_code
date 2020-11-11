import java.util.Scanner;

public class FibonacciNumber {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(fibonacciIterative(n));
    }

    static long fibonacciIterative(int n) {

        if (n <= 1) return n;

        long[] sequence = new long[n + 1];

        sequence[0] = 0;
        sequence[1] = 1;

        for (int i = 2; i <= n; i++) {
            sequence[i] = sequence[i - 1] + sequence[i - 2];
        }

        return sequence[n];
    }
}
