package Week9.Practice.P05_MaximizeAreaBetweenTwoBoundaries;

import java.util.Arrays;
import java.util.Scanner;

public class MaximizeAreaBetweenTwoBoundaries {

    /**
     * Finds the maximum area of water a container can store.
     * Uses two pointers starting at extremes and moving the shorter line inward.
     * Time Complexity: O(N) where N = heights.length.
     * Space Complexity: O(1) auxiliary space.
     */
    public static int maxContainerArea(int[] heights) {
        if (heights == null || heights.length < 2) {
            return 0;
        }

        int maxArea = 0;
        int left = 0;
        int right = heights.length - 1;

        while (left < right) {
            int width = right - left;
            int minHeight = Math.min(heights[left], heights[right]);
            int currentArea = minHeight * width;

            maxArea = Math.max(maxArea, currentArea);

            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    /**
     * Brute-Force checking all pairs of lines.
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     */
    public static int maxContainerAreaBruteForce(int[] heights) {
        if (heights == null || heights.length < 2) {
            return 0;
        }

        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            for (int j = i + 1; j < heights.length; j++) {
                int area = Math.min(heights[i], heights[j]) * (j - i);
                maxArea = Math.max(maxArea, area);
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 5: Maximize Area Between Two Boundaries ===");

        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("Heights: " + Arrays.toString(heights));

        int result = maxContainerArea(heights);
        System.out.println("Expected Output: 49");
        System.out.println("Actual Output (Two-Pointer O(N)):  " + result);
        System.out.println("Actual Output (Brute-Force O(N^2)): " + maxContainerAreaBruteForce(heights));

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.println("Enter space-separated heights:");
                String line = scanner.nextLine().trim();
                int[] customHeights = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();
                System.out.println("Max Container Area: " + maxContainerArea(customHeights));
            }
        }
    }
}
