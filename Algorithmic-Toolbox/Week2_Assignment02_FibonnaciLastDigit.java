import java.util.Scanner;

public class FibonacciNumberLastDigit {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(fibonacciLastDigit(n));
    }

    static int fibonacciLastDigit(int n) {
        if (n <= 1) return n;

        int first = 0;
        int second = 1;
        int temp = 0;

        for (int i = 2; i <= n; i++) {
            temp = (first + second) % 10;
            first = second;
            second = temp;
        }

        return second;
    }
}
