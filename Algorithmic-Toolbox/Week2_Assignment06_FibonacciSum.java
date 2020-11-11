import java.util.Scanner;

public class FibonacciSumLastDigit {
    static long fibonacciSumLastDigit(long n) {
        if (n <= 1)
            return n;

        // Pisano period for Fibonacci mod 10
        final int period = 60;

        int reducedIndex = (int)(n % period);

        int first = 0;
        int second = 1;
        int temp = 0;
        int sum = 0;

        for (int i = 0; i < reducedIndex; i++) {
            temp = (first + second) % 10;
            sum += second;
            first = second;
            second = temp;
        }

        return sum % 10;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = fibonacciSumLastDigit(n);
        System.out.println(s);
    }
}
