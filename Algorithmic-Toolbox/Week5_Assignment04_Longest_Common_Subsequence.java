import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Assignment04_Longest_Common_Subsequence {
    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        System.out.print(lcs(a, b));
    }

    public static int lcs(int[] a, int[] b) {
        int[][] dist = new int[a.length + 1][b.length + 1];

        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= b.length; j++) {
                int max = Math.max(
                        Math.max(
                                dist[i - 1][j],
                                dist[i][j - 1]
                        ),
                        dist[i - 1][j - 1]
                );

                int cost = a[i - 1] == b[j - 1] ? dist[i - 1][j - 1] + 1 : dist[i - 1][j - 1];

                dist[i][j] = Math.max(max, cost);
            }
        }

        return dist[a.length][b.length];
    }

    static class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
