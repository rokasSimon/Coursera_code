package make_heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class MakeHeap {
    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        int n = scanner.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        MinHeap heap = new MinHeap(array);
        heap.buildHeap();
        heap.printAnswers();
    }

    public static class MinHeap {
        private int size;
        private int[] heap;
        private int swapCount;
        private StringBuilder output;

        public MinHeap(int n) {
            size = 0;
            heap = new int[n];
            swapCount = 0;
            output = new StringBuilder();
        }

        public MinHeap(int[] array) {
            size = array.length;
            heap = array;
            swapCount = 0;
            output = new StringBuilder();
        }

        public int getSize() {
            return size;
        }
        public int getCapacity() {
            return heap.length;
        }
        public void printAnswers() {
            output.insert(0, String.format("%d\n", swapCount));

            System.out.print(output.toString());
        }
        public void buildHeap() {
            for (int i = size / 2; i >= 0; i--) {
                siftDown(i);
            }
        }

        private int parentIndex(int i) {
            return (i - 1) / 2;
        }
        private int leftChildIndex(int i) {
            return (i * 2) + 1;
        }
        private int rightChildIndex(int i) {
            return i * 2 + 2;
        }
        private void swap(int i, int j) {
            swapCount++;
            output.append(String.format("%d %d\n", i, j));

            int temp = heap[i];
            heap[i] = heap[j];
            heap[j] = temp;
        }

        private void siftUp(int i) {
            while (i > 0 && heap[parentIndex(i)] > heap[i]) {
                swap(parentIndex(i), i);
                i = parentIndex(i);
            }
        }
        private void siftDown(int i) {
            int minIndex = i;
            if (leftChildIndex(i) < size && heap[leftChildIndex(i)] < heap[minIndex]) {
                minIndex = leftChildIndex(i);
            }
            if (rightChildIndex(i) < size && heap[rightChildIndex(i)] < heap[minIndex]) {
                minIndex = rightChildIndex(i);
            }

            if (minIndex != i) {
                swap(i, minIndex);
                siftDown(minIndex);
            }
        }

        public int extractMin() {
            int max = heap[0];
            heap[0] = heap[size - 1];
            size--;
            siftDown(0);
            return max;
        }
        public void insert(int value) {
            if (size == getCapacity()) throw new IndexOutOfBoundsException(String.format("Heap array size exceeded. Max capacity: %d", getCapacity()));

            heap[size] = value;
            siftUp(size);
            size++;
        }
        public void remove(int index) {
            heap[index] = Integer.MAX_VALUE;
            siftUp(index);
            extractMin();
        }
    }

    public static class FastScanner {
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
    }
}