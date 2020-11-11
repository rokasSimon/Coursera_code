import java.util.Scanner;

public class Assignment03 {
    static int computeMinRefills(int dist, int tank, int[] stops) {
        if (dist <= tank) return 0;

        int end = stops.length - 1;

        int refillCount = 0;
        int currentStop = 0;

        while (currentStop < end) {
            int lastRefillStop = currentStop;
            while (currentStop < end && stops[currentStop + 1] - stops[lastRefillStop] <= tank) {
                currentStop++;
            }
            if (currentStop == lastRefillStop) return -1;
            if (currentStop < end) refillCount++;
        }

        return refillCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int[] stops = new int[n + 2];
        stops[0] = 0;
        stops[stops.length - 1] = dist;
        for (int i = 1; i <= n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}
