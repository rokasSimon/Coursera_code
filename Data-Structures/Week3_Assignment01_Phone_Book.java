import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Assignment01_Phone_Book {
    public static void main(String[] args) {
        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();
        PhoneBook phoneBook = new PhoneBook();

        for (int i = 0; i < n; i++) {
            String keyword = scanner.next();

            switch (keyword) {
                case "add": {
                    int number = scanner.nextInt();
                    String name = scanner.next();
                    phoneBook.add(number, name);
                } break;
                case "del": {
                    int number = scanner.nextInt();
                    phoneBook.del(number);
                } break;
                case "find": {
                    int number = scanner.nextInt();
                    System.out.println(phoneBook.find(number));
                } break;
            }
        }
    }

    public static class PhoneBook {
        private final String[] names;

        public PhoneBook() {
            this.names = new String[10000000];
        }

        public void add(int number, String name) {
            this.names[number] = name;
        }

        public void del(int number) {
            this.names[number] = null;
        }

        public String find(int number) {
            if (this.names[number] == null) {
                return "not found";
            }
            return this.names[number];
        }
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
