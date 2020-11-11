
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class ParallelProcessing {
    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner();
        int threads = scanner.nextInt();
        int tasks   = scanner.nextInt();
        long[] array = new long[tasks];
        PriorityQueue<Task> queue = new PriorityQueue<>(threads, (task, t1) -> {
            if (task.finishTime < t1.finishTime
                    || task.finishTime == t1.finishTime && task.queueTime < t1.queueTime) return -1;
            else if (task.finishTime == t1.finishTime && task.queueTime == t1.queueTime) return 0;
            return 1;
        });
        PriorityQueue<Integer> freeThreads = new PriorityQueue<>(threads);

        for (int i = 0; i < threads; i++) {
            freeThreads.add(i);
        }

        for (int i = 0; i < tasks; i++) {
            array[i] = scanner.nextInt();
        }

        StringBuilder output = new StringBuilder();
        long currentTime = 0;
        int queueTime   = 0;
        int taskIndex   = 0;

        /*while (taskIndex < tasks) {
            if (!queue.isEmpty()) {
                Task done   = queue.poll();
                currentTime = done.finishTime;
                freeThread  = done.thread;
            }
            while (queue.size() < threads && taskIndex < array.length) {
                output.append(String.format("%d %d", freeThread, currentTime)).append('\n');
                queue.add(new Task(freeThread, currentTime + array[taskIndex], queueTime));
                taskIndex++;
                freeThread++;
                queueTime++;
            }
        }*/

        while (taskIndex < tasks) {
            while (!queue.isEmpty() && queue.peek().finishTime == currentTime) {
                Task done = queue.poll();
                freeThreads.add(done.thread);
            }
            while (queue.size() < threads && taskIndex < array.length) {
                int freeThread = freeThreads.poll();
                output.append(String.format("%d %d", freeThread, currentTime)).append('\n');
                queue.add(new Task(freeThread, currentTime + array[taskIndex], queueTime));
                taskIndex++;
                queueTime++;
            }
            currentTime = queue.peek().finishTime;
        }

        System.out.print(output.toString());
    }

    static class Task {
        private final int thread;
        private final long finishTime;
        private final int queueTime;

        public Task(int thread, long time, int queueTime) {
            this.thread = thread;
            this.finishTime = time;
            this.queueTime = queueTime;
        }

        @Override
        public String toString() {
            return String.format("%d %d", thread, finishTime);
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
