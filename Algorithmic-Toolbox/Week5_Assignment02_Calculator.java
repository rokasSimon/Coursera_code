import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Assignment02_Calculator {
    public static void main(String[] args) throws IOException {

        FastScanner scanner = new FastScanner();
        StringBuilder sequence = new StringBuilder();
        int n = scanner.nextInt();

        if (n < 4) {
            if (n == 1) {
                sequence.append("0\n1");
            }
            else sequence.append("1\n1 ").append(n);

            System.out.print(sequence.toString());
            return;
        }

        int[] numbers = new int[n + 1];
        numbers[1] = 1;
        numbers[2] = 1;
        numbers[3] = 1;

        for (int i = 4; i <= n; i++) {
            if (i % 3 == 0) {
                int reduced = numbers[i / 3] + 1;
                numbers[i] = Math.min(numbers[i - 1] + 1, reduced);
            }
            else if (i % 2 == 0) {
                int reduced = numbers[i / 2] + 1;
                numbers[i] = Math.min(numbers[i - 1] + 1, reduced);
            }
            else numbers[i] = numbers[i - 1] + 1;
        }

        sequence.append(numbers[n]).append('\n');
        generateSequence(numbers, sequence, n);

        System.out.print(sequence.toString());
    }

    public static void generateSequence(int[] steps, StringBuilder sequence, int n) {
        if (n < 2) {
            sequence.append("1 ");
            return;
        }

        int minStep = Integer.MAX_VALUE;
        int current = n;

        if (n % 3 == 0 && steps[n / 3] < minStep) {
            current = n / 3;
            minStep = steps[n / 3];
        }
        else if (n % 2 == 0 && steps[n / 2] < minStep) {
            current = n / 2;
            minStep = steps[n / 2];
        }
        if (steps[n - 1] < minStep) {
            current = n - 1;
        }

        generateSequence(steps, sequence, current);
        sequence.append(n).append(' ');
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
