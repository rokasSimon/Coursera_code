import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Assignment02 {
    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        if (capacity == 0) return 0;

        double totalValue = 0;
        int capacityLeft = capacity;
        HashMap<Integer, Integer> ignore = new HashMap<>();

        while (capacityLeft > 0 && values.length != ignore.size()) {
            int maxIndex = getMaxValuePerWeightIndex(values, weights, ignore);
            if (capacityLeft > weights[maxIndex]) {
                capacityLeft -= weights[maxIndex];
                totalValue += values[maxIndex];
                ignore.put(maxIndex, maxIndex);
            }
            else {
                totalValue += values[maxIndex] * ((double)capacityLeft / weights[maxIndex]);
                capacityLeft = 0;
            }

            /*if (weights[maxIndex] > capacityLeft) {
                double ratio = (double)capacityLeft / weights[maxIndex];
                totalValue += values[maxIndex] * ratio;
                capacityLeft = 0;
            }
            else {
                capacityLeft -= weights[maxIndex];
                totalValue += values[maxIndex];
                ignore.add(maxIndex);
            }*/
        }

        return totalValue;
    }

    private static int getMaxValuePerWeightIndex(int[] values, int[] weight, HashMap<Integer, Integer> ignore) {
        int index = 0;

        for (int i = 0; i < values.length; i++) {
            if (ignore.containsKey(i)) continue;
            if ((double)values[i] / weight[i] > (double)values[index] / weight[index]) index = i;
        }

        return index;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        double value = getOptimalValue(capacity, values, weights);
        System.out.printf("%.4f", value);
    }
}
