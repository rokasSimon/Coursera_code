import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Assignment02_Check_BST {

    public static void main(String[] args) {
        new Thread(null, () -> {

            FastScanner scanner = new FastScanner();

            int n = 0;
            int[][] orders = null;
            try {
                n = scanner.nextInt();
                if (n == 0) {
                    System.out.print("CORRECT");
                    return;
                }
                orders = new int[n][3];
                for (int i = 0; i < n; i++) {
                    orders[i][0] = scanner.nextInt();
                    orders[i][1] = scanner.nextInt();
                    orders[i][2] = scanner.nextInt();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }


            try {
                Tree tree = new Tree(orders, n);

                System.out.print("CORRECT");
            }
            catch (Exception ignored) {
                System.out.print("INCORRECT");
            }

        }, "1", 1 << 26).start();
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

    static class Tree {
        static class Node {
            int value;
            Node left;
            Node right;
            int min;
            int max;

            public Node(int key, Node left, Node right, int min, int max) {
                this.value = key;
                this.left = left;
                this.right = right;
                this.min = min;
                this.max = max;
            }

            public Node(int key, Node left, Node right) {
                this.value = key;
                this.left = left;
                this.right = right;
            }
        }

        Node head;
        int size;

        public Tree(int[][] orders, int n) throws Exception {
            this.head = new Node(orders[0][0], null, null, Integer.MIN_VALUE, Integer.MAX_VALUE);
            this.size = n;
            if (orders[0][1] != -1) {
                this.head.left = addRecursive(orders[0][1], orders, this.head.min, this.head.value);
            }
            if (orders[0][2] != -1) {
                this.head.right = addRecursive(orders[0][2], orders, this.head.value, this.head.max);
            }
        }

        private Node addRecursive(int i, int[][] orders, int p_min, int p_max) throws Exception {
            Node node = new Node(orders[i][0], null, null);

            if (node.value > p_min && node.value < p_max) {
                node.min = p_min;
                node.max = p_max;
            }
            else {
                throw new Exception("INCORRECT");
            }
            /*if (left && node.value < p_min) {
                node.min = node.value;
                node.max = p_max;
            }
            else if (!left && node.value > p_max) {
                node.min = p_max;
                node.max = node.value;
            }
            else if (node.value < p_max && node.value > p_min) {
                node.min = p_min;
                node.max = p_max;
            }
            else correct = false;*/

            if (orders[i][1] != -1) {
                node.left = addRecursive(orders[i][1], orders, node.min, node.value);
            }
            if (orders[i][2] != -1) {
                node.right = addRecursive(orders[i][2], orders, node.value, node.max);
            }
            return node;
        }
    }
}
