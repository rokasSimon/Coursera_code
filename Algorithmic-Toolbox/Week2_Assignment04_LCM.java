import java.util.Scanner;

public class LeastCommonMultiple {
    static long leastCommonMultiple(int a, int b) {
        long gcd = euclideanGCD(a, b);

        return ((long)a * b) / gcd;
    }

    static long euclideanGCD(int a, int b) {
        if (b == 0) return a;
        return euclideanGCD(b, a % b);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(leastCommonMultiple(a, b));
    }
}
