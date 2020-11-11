import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Assignment01_Binary_Tree_Traversal {
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new Assignment01_Binary_Tree_Traversal().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }

    public void run() throws IOException {
        FastScanner scanner = new FastScanner();

        int n = scanner.nextInt();
        int[][] orders = new int[n][3];

        for (int i = 0; i < n; i++) {
            orders[i][0] = scanner.nextInt();
            orders[i][1] = scanner.nextInt();
            orders[i][2] = scanner.nextInt();
        }

        Tree tree = new Tree(orders, n);

        tree.inorder();
        tree.postorder();
        tree.preorder();
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

            public Node(int key, Node left, Node right) {
                this.value = key;
                this.left = left;
                this.right = right;
            }
        }

        Node head;
        int size = 0;

        public Tree(int[][] orders, int n) {
            this.head = new Node(orders[0][0], null, null);
            this.size = n;
            if (orders[0][1] != -1) {
                this.head.left = addRecursive(orders[0][1], orders);
            }
            if (orders[0][2] != -1) {
                this.head.right = addRecursive(orders[0][2], orders);
            }
        }

        private Node addRecursive(int i, int[][] orders) {
            Node node = new Node(orders[i][0], null, null);
            if (orders[i][1] != -1) {
                node.left = addRecursive(orders[i][1], orders);
            }
            if (orders[i][2] != -1) {
                node.right = addRecursive(orders[i][2], orders);
            }
            return node;
        }

        public void inorder() {
            if (this.head == null) return;

            StringBuilder output = new StringBuilder();
            Deque<Node> stack = new ArrayDeque<>();

            Node current = this.head;
            while (!stack.isEmpty() || current != null) {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }

                current = stack.pop();
                output.append(current.value).append(' ');
                current = current.right;
            }

            System.out.println(output.toString());
        }

        public void postorder() {
            if (this.head == null) return;

            StringBuilder output = new StringBuilder();
            Deque<Node> stack = new ArrayDeque<>();
            Deque<Integer> outputStack = new ArrayDeque<>();

            stack.push(this.head);

            while (!stack.isEmpty()) {
                Node popped = stack.pop();
                outputStack.push(popped.value);

                if (popped.right != null) stack.push(popped.right);
                if (popped.left != null) stack.push(popped.left);
            }

            while (!outputStack.isEmpty()) {
                output.append(outputStack.removeLast()).append(' ');
            }

            System.out.println(output.toString());
        }

        public void preorder() {
            if (this.head == null) return;

            StringBuilder output = new StringBuilder();

            Deque<Node> stack = new ArrayDeque<>();
            Deque<Integer> outputStack = new ArrayDeque<>();

            stack.push(this.head);

            while (!stack.isEmpty()) {
                Node popped = stack.pop();
                outputStack.push(popped.value);

                if (popped.left != null) stack.push(popped.left);
                if (popped.right != null) stack.push(popped.right);
            }

            while (!outputStack.isEmpty()) {
                output.append(outputStack.pop()).append(' ');
            }

            System.out.print(output.toString());
        }
    }
}
