import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Assignment01_Money_Change {
    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        int money = scanner.nextInt();
        int[] denominations = {1, 3, 4};
        int[] coins = new int[money + 1];

        for (int i = 1; i <= money; i++) {
            coins[i] = Integer.MAX_VALUE;
            for (int denomination : denominations) {
                if (i >= denomination) {
                    int numCoins = coins[i - denomination] + 1;
                    if (numCoins < coins[i]) {
                        coins[i] = numCoins;
                    }
                }
            }
        }

        System.out.print(coins[money]);
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
