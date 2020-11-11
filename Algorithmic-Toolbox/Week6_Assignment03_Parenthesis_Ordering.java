import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Assignment03_Parenthesis_Ordering {
    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        String expression = scanner.next();

        char[] operators = new char[expression.length() / 2];
        int[] operands = new int[expression.length() / 2 + 1];

        for (int i = 0; i < expression.length(); i++) {
            if (i % 2 == 0) {
                operands[i / 2] = expression.charAt(i) - 48;
            }
            else {
                operators[i / 2] = expression.charAt(i);
            }
        }

        long[][] minMatrix = new long[operands.length][operands.length];
        long[][] maxMatrix = new long[operands.length][operands.length];

        for (int i = 0; i < operands.length; i++) {
            minMatrix[i][i] = maxMatrix[i][i] = operands[i];
        }

        /*
             0 1 2 3 4 5
             1 2 3 4 5 6

       1 0   5 0 0 0 0 0
       2 1   0 8 0 0 0 0
       3 2   0 0 7 0 0 0
       4 3   0 0 0 4 0 0
       5 4   0 0 0 0 8 0
       6 5   0 0 0 0 0 9

         */

        for (int i = 0; i < operands.length - 1; i++) {
            for (int j = 0; j < operands.length - i - 1; j++) {
                int diag = i + j + 1;

                long min = Long.MAX_VALUE;
                long max = Long.MIN_VALUE;
                for (int k = j; k <= diag - 1; k++) {
                    long a = op (maxMatrix[j][k], operators[k], maxMatrix[k + 1][diag]);
                    long b = op (maxMatrix[j][k], operators[k], minMatrix[k + 1][diag]);
                    long c = op (minMatrix[j][k], operators[k], maxMatrix[k + 1][diag]);
                    long d = op (minMatrix[j][k], operators[k], minMatrix[k + 1][diag]);
                    min = min5(min, a, b, c, d);
                    max = max5(max, a, b, c, d);
                }

                minMatrix[j][diag] = min;
                maxMatrix[j][diag] = max;
            }
        }

        System.out.println(maxMatrix[0][operands.length - 1]);
    }

    public static long max5(long a, long b, long c, long d, long e) {
        return Math.max(
                Math.max(
                        Math.max(a, b),
                        Math.max(c, d)),
                e
        );
    }

    public static long min5(long a, long b, long c, long d, long e) {
        return Math.min(
                Math.min(
                        Math.min(a, b),
                        Math.min(c, d)
                ),
                e
        );
    }

    public static long op(long x, char sym, long y) {
        switch (sym) {
            case '+': return x + y;
            case '-': return x - y;
            default: return x * y;
        }
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
            StringBuilder output = new StringBuilder();
            for (int[] ints : matrix) {
                for (int anInt : ints) {
                    output.append(anInt).append("\t");
                }
                output.append("\n");
            }
            return output.toString();
        }

        public static String longArrayToString(long[][] matrix) {
            StringBuilder output = new StringBuilder();
            for (long[] longs : matrix) {
                for (long aLong : longs) {
                    output.append(aLong).append("\t");
                }
                output.append("\n");
            }
            return output.toString();
        }
    }
}
