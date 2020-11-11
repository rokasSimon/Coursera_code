import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;

class MaximumPairwiseProduct {

    static long getMaxPairwiseProductFast(int[] numbers) {
        int index1 = 0;
        int index2 = 1;
        boolean prioritySecond = false;

        for (int i = 2; i < numbers.length; i++) {
            if (prioritySecond && numbers[i] > numbers[index2]) {
                if (numbers[index2] > numbers[index1]) index1 = index2;
                index2 = i;
                prioritySecond = false;
            }
            else if (numbers[i] > numbers[index1]) {
                if (numbers[index1] > numbers[index2]) index2 = index1;
                index1 = i;
                prioritySecond = true;
            }
            else if (numbers[i] > numbers[index2]) {
                if (numbers[index2] > numbers[index1]) index1 = index2;
                index2 = i;
            }
        }

        /* Debugging
        System.out.printf("First Index = %d :: Number = %d%n", index1, numbers[index1]);
        System.out.printf("Second Index = %d :: Number = %d%n", index2, numbers[index2]);
        */

        return (long)numbers[index1] * numbers[index2];
    }
    /*
    static long getMaxPairwiseProductProven(int[] numbers) {
        Arrays.sort(numbers);
        System.out.println("Proven method...");
        System.out.println(Arrays.toString(numbers));
        int first = numbers[numbers.length - 1];
        int second = numbers[numbers.length - 2];

        return (long)first * second;
    }

    static void testMethods(int testCount) {
        Random rand = new Random();

        int upperCountBound = 20;
        int lowerCountBound = 2;
        int upperNumberBound = 200000;
        int lowerNumberBound = 1;

        int wrongCount = 0;
        int rightCount = 0;

        for (int i = 0; i < testCount; i++) {
            int N = rand.nextInt(upperCountBound - lowerCountBound) + lowerCountBound;

            int[] numbers = new int[N];

            for (int j = 0; j < N; j++) {
                numbers[j] = rand.nextInt(upperNumberBound - lowerNumberBound) + lowerNumberBound;
            }

            System.out.println(Arrays.toString(numbers));

            var fast = getMaxPairwiseProductFast(numbers);
            var proven = getMaxPairwiseProductProven(numbers);

            if (fast == proven) {
                System.out.println("OK");
                rightCount++;
            }
            else {
                System.out.printf("WRONG: Fast = %d :: Proven = %d%n", fast, proven);
                wrongCount++;
            }

            System.out.printf("Correct tests: %d :: Wrong tests: %d", rightCount, wrongCount);
        }
    }
    */
    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.println(getMaxPairwiseProductFast(numbers));

        //testMethods(1000);
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new
                        InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
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
}
