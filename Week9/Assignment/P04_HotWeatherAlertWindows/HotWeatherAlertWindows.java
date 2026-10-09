package Week9.Assignment.P04_HotWeatherAlertWindows;

import java.util.Arrays;
import java.util.Scanner;

public class HotWeatherAlertWindows {

    /**
     * Counts the number of k-hour consecutive blocks whose average temperature >= threshold.
     * Uses the sliding window technique to maintain the running sum in O(1) time per step.
     * Time Complexity: O(N) where N = readings.length.
     * Space Complexity: O(1) auxiliary space.
     */
    public static int countAlerts(int[] readings, int k, int threshold) {
        if (readings == null || readings.length < k || k <= 0) {
            return 0;
        }

        long targetSum = (long) k * threshold;
        long currentWindowSum = 0;
        int alertCount = 0;

        // Calculate sum for first window
        for (int i = 0; i < k; i++) {
            currentWindowSum += readings[i];
        }

        if (currentWindowSum >= targetSum) {
            alertCount++;
        }

        // Slide the window across the rest of the array
        for (int i = k; i < readings.length; i++) {
            currentWindowSum += readings[i] - readings[i - k];
            if (currentWindowSum >= targetSum) {
                alertCount++;
            }
        }

        return alertCount;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 4: Hot Weather Alert Windows ===");

        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};
        int k = 3;
        int threshold = 4;

        System.out.println("Readings:  " + Arrays.toString(readings));
        System.out.println("k:         " + k);
        System.out.println("Threshold: " + threshold);

        int result = countAlerts(readings, k, threshold);
        System.out.println("Expected Output: 3 // blocks [2, 5, 5], [5, 5, 5], [5, 5, 8]");
        System.out.println("Actual Output:   " + result);

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.println("Enter space-separated temperature readings:");
                String line = scanner.nextLine().trim();
                int[] customReadings = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();
                System.out.print("Enter window size k: ");
                int customK = scanner.nextInt();
                System.out.print("Enter threshold: ");
                int customThresh = scanner.nextInt();
                System.out.println("Alert Windows Count: " + countAlerts(customReadings, customK, customThresh));
            }
        }
    }
}
