import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Assignment02_Chain_Hashing {

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner();
        int buckets = scanner.nextInt();
        int queries = scanner.nextInt();
        HashTable<String, String> hashTable = new HashTable<>(buckets, s -> {
            final int prime = 1000000007;
            final int x = 263;
            long sum = 0;

            for (int i = s.length() - 1; i >= 0; i--) {
                char character = s.charAt(i);
                sum = (sum * x + character) % prime;
            }

            return (int)(sum % buckets);
        });

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < queries; i++) {
            String keyword = scanner.next();

            switch (keyword) {
                case "add": {
                    String key = scanner.next();
                    hashTable.add(key, key);
                } break;
                case "check": {
                    int j = scanner.nextInt();
                    output.append(hashTable.check(j)).append('\n');
                } break;
                case "find": {
                    String key = scanner.next();
                    output.append(hashTable.has(key) ? "yes\n" : "no\n");
                } break;
                case "del": {
                    String key = scanner.next();
                    hashTable.remove(key);
                } break;
            }
        }

        System.out.print(output.toString());

    }

    public static class HashTable<TKey, TValue> {
        @FunctionalInterface
        public interface HashFunction<TKey> {
            int hash(TKey key);
        }

        class Entry {
            TKey key;
            TValue value;
            Entry next;

            public Entry(TKey key, TValue value, Entry next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }

            public void add(TKey key, TValue value) {
                if (this.key == null) {
                    this.key = key;
                    this.value = value;
                }
                else {
                    Entry next = new Entry(this.key, this.value, this.next);

                    this.key = key;
                    this.value = value;
                    this.next = next;
                }
            }

            public void remove(TKey key) {
                if (this.key == null) {
                    return;
                }
                else if (this.key.equals(key)) {
                    if (this.next == null) {
                        this.key = null;
                        this.value = null;
                    }
                    else {
                        this.key = next.key;
                        this.value = next.value;
                        this.next = next.next;
                    }
                    return;
                }

                Entry before = this;
                Entry current = before.next;

                while (current != null && !current.key.equals(key)) {
                    before = current;
                    current = current.next;
                }

                if (current == null) return;

                before.next = current.next;
            }

            public boolean has(TKey key) {
                Entry current = this;

                while (current != null) {
                    if (current.key != null && current.key.equals(key)) {
                        return true;
                    }
                    current = current.next;
                }

                return false;
            }

            public TValue get(TKey key)  {
                Entry current = this;

                while (current != null) {
                    if (current.key.equals(key)) {
                        return current.value;
                    }
                    current = current.next;
                }

                return null;
            }

            private Entry getEntry(TKey key) {
                Entry current = this;

                while (current != null) {
                    if (current.key.equals(key)) {
                        return current;
                    }
                    current = current.next;
                }

                return null;
            }
        }

        private final ArrayList<Entry> buckets;
        private int size;
        private final HashFunction<TKey> hasher;

        public HashTable(int capacity, HashFunction<TKey> hFunc) {
            hasher = hFunc;
            buckets = new ArrayList<>(capacity);

            for (int i = 0; i < capacity; i++) {
                buckets.add(new Entry(null, null, null));
            }
        }

        public TValue get(TKey key) {
            int hashedKey = hasher.hash(key);

            return buckets.get(hashedKey).get(key);
        }

        public boolean has(TKey key) {
            int hashedKey = hasher.hash(key);

            return buckets.get(hashedKey).has(key);
        }

        public void add(TKey key, TValue value) {
            int hashedKey = hasher.hash(key);
            Entry bucket = buckets.get(hashedKey);

            if (!bucket.has(key)) {
                bucket.add(key, value);
                size++;
            }
        }

        public void remove(TKey key) {
            int hashedKey = hasher.hash(key);

            buckets.get(hashedKey).remove(key);
        }

        public String check(int i) {
            Entry entry = buckets.get(i);
            StringBuilder output = new StringBuilder();

            if (entry.key == null) return "";

            while (entry != null) {
                output.append(entry.value).append(' ');
                entry = entry.next;
            }

            return output.toString();
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
