import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Assignment03_Text_Pattern {
    static final int prime = 1000000007;
    static final int x = 1;

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner();

        String searchFor = scanner.next();
        String text      = scanner.next();

        RabinKarpSearch(text, searchFor);
    }

    public static void RabinKarpSearch(String text, String searchFor) {
        StringBuilder output = new StringBuilder();
        long searchHash = polyHash(searchFor);
        long[] hashes = precomputeHashes(text,searchFor.length());

        for (int i = 0; i <= text.length() - searchFor.length(); i++) {
            if (searchHash == hashes[i] && text.startsWith(searchFor, i)) {
                output.append(i).append(' ');
            }
        }

        System.out.print(output.toString());
    }

    public static long polyHash(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            hash = (hash * x + s.charAt(i)) % prime;
        }
        return hash;
    }

    public static long[] precomputeHashes(String text, int substringLength) {
        long[] hashes = new long[text.length() - substringLength + 1];
        String s = text.substring(text.length() - substringLength);
        hashes[text.length() - substringLength] = polyHash(s);

        long y = 1;
        for (int i = 1; i <= substringLength; i++) {
            y = (y * x) % prime;
        }
        for (int i = text.length() - substringLength - 1; i >= 0; i--) {
            hashes[i] = ((x * hashes[i + 1] + (long)text.charAt(i) - y * (long)text.charAt(i + substringLength)) % prime + prime) % prime;
        }

        return hashes;
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
}
