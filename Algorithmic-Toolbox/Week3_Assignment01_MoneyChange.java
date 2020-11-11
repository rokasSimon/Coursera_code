import java.util.Scanner;

public class Assignment01 {
    private static int getChange(int m) {
        int sum = 0;
        int left = m;

        if (left >= 10) {
            sum += left / 10;
            left = left % 10;
        }
        if (left >= 5) {
            sum += left / 5;
            left = left % 5;
        }
        if (left >= 1) {
            sum += left;
        }

        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}
