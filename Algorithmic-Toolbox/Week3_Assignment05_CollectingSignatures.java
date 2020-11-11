import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Assignment05 {
    private static ArrayList<Integer> optimalPoints(Segment[] segments) {

        ArrayList<Integer> points = new ArrayList<>();

        Arrays.sort(segments, (segment, t1) -> {
            if (segment.end < t1.end) return 1;
            else if (segment.end == t1.end) return 0;
            return -1;
        });

        int currentMin = segments[0].start;
        int currentMax = segments[0].end;

        for (int i = 1; i < segments.length; i++) {
            if (segments[i].end >= currentMin) {
                currentMax = segments[i].end;
                if (segments[i].start >= currentMin) currentMin = segments[i].start;
            }
            else {
                points.add(currentMax);
                currentMax = segments[i].end;
                currentMin = segments[i].start;
            }
        }

        points.add(currentMax);

        return points;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        ArrayList<Integer> points = optimalPoints(segments);
        System.out.println(points.size());
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
