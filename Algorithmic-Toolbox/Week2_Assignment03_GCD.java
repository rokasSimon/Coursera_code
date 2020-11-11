import java.util.Scanner;

public class GreatestCommonDivisor {
    static int euclideanGCD(int a, int b) {
        if (b == 0) return a;
        return euclideanGCD(b, a % b);
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(euclideanGCD(a, b));
    }
}
