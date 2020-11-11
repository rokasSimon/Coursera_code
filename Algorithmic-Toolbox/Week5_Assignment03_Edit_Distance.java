import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Assignment03_Edit_Distance {
    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        String str1 = scanner.next().toLowerCase();
        String str2 = scanner.next().toLowerCase();

        System.out.print(editDistance(str1, str2));
    }

    public static int editDistance(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        if (n == 0) {
            return m;
        }
        else if (m == 0) {
            return n;
        }

        int[][] dist = new int[n + 1][m + 1];

        for (int i = 0; i <= n; dist[i][0] = i++) {}
        for (int j = 0; j <= m; dist[0][j] = j++) {}

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int cost = (str2.charAt(j - 1) == str1.charAt(i - 1) ? 0 : 1);

                dist[i][j] = Math.min(
                        Math.min(
                                dist[i - 1][j] + 1,
                                dist[i][j - 1] + 1),
                        dist[i - 1][j - 1] + cost
                );
            }
        }

        return dist[n][m];
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
