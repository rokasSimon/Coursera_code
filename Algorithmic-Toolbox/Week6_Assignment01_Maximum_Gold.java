import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Assignment01_Maximum_Gold {
    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();

        int capacity = scanner.nextInt();
        int n = scanner.nextInt();

        int[] weights = new int[n];
        int[][] maxWeights = new int[n + 1][capacity + 1];

        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }

        /*
        capacity = 10, n = 3
        1 4 8

            0   1   2   3   4   5   6   7   8   9   10
        0   0   0   0   0   0   0   0   0   0   0   0
      (1)1  0   1   1   1   1   1   1   1   1   1   1
      (4)2  0   1   1   1   4   5   5   5   5   5   5
      (8)3  0   1   1   1   4   5   5   5   8   9   9
         */

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                maxWeights[i][j] = maxWeights[i - 1][j];
                if (weights[i - 1] <= j) {
                    int val = maxWeights[i - 1][j - weights[i - 1]] + weights[i - 1];
                    if (val > maxWeights[i][j]) {
                        maxWeights[i][j] = val;
                    }
                }
            }
        }

        System.out.println(maxWeights[n][capacity]);
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public static String get2DArrayPrint(int[][] matrix) {
            String output = new String();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    output = output + (matrix[i][j] + "\t");
                }
                output = output + "\n";
            }
            return output;
        }
    }
}
