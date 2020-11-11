import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Assignment07 {
    private static String largestNumber(String[] a) {
        StringBuilder result = new StringBuilder();

        Arrays.sort(a, (current, next) -> {
            String y = current + next;
            String x = next + current;
            return Integer.parseInt(x) - Integer.parseInt(y);
        });

        for (String str : a) {
            result.append(str);
        }

        return result.toString();
    }



    /*static int stringValueCompare(String a, String b) {
        int i = 0;
        int j = 0;

        while (i < a.length() || j < b.length()) {
            if (a.charAt(i) > b.charAt(j)) return -1;
            if (a.charAt(i) < b.charAt(j)) return 1;
            if (i < a.length() - 1 || i == 0 && b.length() == 1) i++;
            if (j < b.length() - 1 || j == 0 && a.length() == 1) j++;
        }

        if (a.length() > b.length()) return -1;
        else if (a.length() < b.length()) return 1;

        return 0;
    }*/

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}
