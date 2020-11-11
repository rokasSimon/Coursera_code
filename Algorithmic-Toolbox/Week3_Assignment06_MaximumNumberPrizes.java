import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment06 {
    private static List<Integer> optimalSummands(int n) {
        ArrayList<Integer> summands = new ArrayList<Integer>();

        int left = n;
        int next = 1;

        while (left > 0) {
            if (next + (next + 1) > left) {
                summands.add(left);
                return summands;
            }
            else {
                summands.add(next);
                left -= next;
                next++;
            }
        }

        return summands;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}
