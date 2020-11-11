import java.util.Scanner;

public class FibonacciSumRangeLastDigit {
    static long fibonacciSumRangeLastDigit(long n, long m) {
        if (m <= 1)
            return m;

        // Pisano period for Fibonacci mod 10
        final int period = 60;

        //long reducedEndIndex = (n + (m - n) % period);
        long reducedEndIndex = (m % period) + 60;
        long reducedStartIndex = (n % period);

        int first = 0;
        int second = 1;
        int temp = 0;
        long sum = 0;

        for (int i = 1; i < reducedEndIndex + 1; i++) {
            if (i >= reducedStartIndex) sum = (sum + second) % 10;
            temp = (first + second) % 10;
            first = second;
            second = temp;
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(fibonacciSumRangeLastDigit(from, to));
    }
}
