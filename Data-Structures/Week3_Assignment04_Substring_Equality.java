import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Assignment04_Substring_Equality {
    public static void main(String[] args) {
        final int m1 = 1000000007;
        final int m2 = 1000000009;
        final int  x = 3;

        FastScanner scanner = new FastScanner();
        StringBuilder out = new StringBuilder();
        String text = scanner.next();
        int n = scanner.nextInt();

        long[] h1 = new long[text.length() + 1];
        long[] h2 = new long[text.length() + 1];
        long[] powers1 = new long[500000];
        long[] powers2 = new long[500000];
        powers1[0] = 1;
        powers2[0] = 1;

        for (int i = 1; i <= text.length(); i++) {
            powers1[i] = ((powers1[i - 1] % m1) * (x % m1)) % m1;
            powers2[i] = ((powers2[i - 1] % m2) * (x % m2)) % m2;
        }

        for (int i = 1; i <= text.length(); i++) {
            h1[i] = (x * h1[i - 1] + text.charAt(i - 1)) % m1;
            h2[i] = (x * h2[i - 1] + text.charAt(i - 1)) % m2;
        }

        for (int i = 0; i < n; i++) {
            int first = scanner.nextInt();
            int second = scanner.nextInt();
            int length = scanner.nextInt();

            long hash1 = (h1[first + length] - (powers1[length] * h1[first]) % m1);
            long hash2 = (h2[second + length] - (powers2[length] * h2[second]) % m2);

            if (hash1 % m1 == hash2 % m1 && hash1 % m2 == hash2 % m2) {
                out.append("Yes\n");
            }
            else {
                out.append("No\n");
            }

            /*var substr1 = text.substring(first, first + length);
            var substr2 = text.substring(second, second + length);
            if (substr1.equals(substr2)) {
                out.append("[Yes] ");
            }
            else {
                out.append("[No] ");
            }
            out.append(i).append('\n');*/
        }

        out.deleteCharAt(out.length() - 1);

        System.out.print(out.toString());
    }

    public static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    /*
abacabadabacaba
120
0 0 1
0 0 2
0 0 3
0 0 4
0 0 5
0 0 6
0 0 7
0 0 8
0 0 9
0 0 10
0 0 11
0 0 12
0 0 13
0 0 14
0 0 15
0 1 1
0 1 2
0 1 3
0 1 4
0 1 5
0 1 6
0 1 7
0 1 8
0 1 9
0 1 10
0 1 11
0 1 12
0 1 13
0 1 14
0 2 1
0 2 2
0 2 3
0 2 4
0 2 5
0 2 6
0 2 7
0 2 8
0 2 9
0 2 10
0 2 11
0 2 12
0 2 13
0 3 1
0 3 2
0 3 3
0 3 4
0 3 5
0 3 6
0 3 7
0 3 8
0 3 9
0 3 10
0 3 11
0 3 12
0 4 1
0 4 2
0 4 3
0 4 4
0 4 5
0 4 6
0 4 7
0 4 8
0 4 9
0 4 10
0 4 11
0 5 1
0 5 2
0 5 3
0 5 4
0 5 5
0 5 6
0 5 7
0 5 8
0 5 9
0 5 10
0 6 1
0 6 2
0 6 3
0 6 4
0 6 5
0 6 6
0 6 7
0 6 8
0 6 9
0 7 1
0 7 2
0 7 3
0 7 4
0 7 5
0 7 6
0 7 7
0 7 8
0 8 1
0 8 2
0 8 3
0 8 4
0 8 5
0 8 6
0 8 7
0 9 1
0 9 2
0 9 3
0 9 4
0 9 5
0 9 6
0 10 1
0 10 2
0 10 3
0 10 4
0 10 5
0 11 1
0 11 2
0 11 3
0 11 4
0 12 1
0 12 2
0 12 3
0 13 1
0 13 2
0 14 1
     */
}
