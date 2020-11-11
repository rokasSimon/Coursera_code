import java.util.Scanner;

public class FibonacciSquareSumLastDigit {
    static long fibonacciSquareSum(long n) {
        if (n <= 1)
            return n;

        // Pisano period length of mod 10
        final int period = 60;
        long reducedIndex = n % period;

        int first = 0;
        int second = 1;
        int temp = 0;

        for (int i = 0; i < reducedIndex; i++) {
            temp = (first + second) % 10;
            first = second;
            second = temp;
        }

        return (first * second) % 10;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = fibonacciSquareSum(n);
        System.out.println(s);
    }
}
