import java.util.Arrays;
import java.util.Scanner;

public class FibonacciModulo {
    static long fibonacciModulo(long n, int m) {
        if (n <= 1) return n;

        int[] moduloCache = new int[(m * m) + 1];
        moduloCache[0] = 0;
        moduloCache[1] = 1;

        int period = pisanoPeriodLength(m, moduloCache);

        if (n > period) {
            int reducedIndex = (int)(n % period);
            return moduloCache[reducedIndex];
        }
        else {
            return moduloCache[(int)n];
        }
    }

    static int pisanoPeriodLength(int m, int[] cache) {
        for (int i = 2; i < cache.length; i++) {
            cache[i] = (cache[i - 1] + cache[i - 2]) % m;
            if (cache[i - 1] == 0 && cache[i] == 1) return i - 1;
        }

        throw new UnsupportedOperationException("Should have found period by now...");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        int m = scanner.nextInt();
        System.out.println(fibonacciModulo(n, m));
    }
}
