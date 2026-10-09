package Week9.Assignment.P02_MergingTwoTokenQueues;

import java.util.*;

public class MergingTwoTokenQueues {

    /**
     * Merges two sorted lists of token numbers into a single sorted list.
     * Uses two-pointer technique to achieve linear time without extra sorting.
     * Time Complexity: O(m + n) where m = counterA.length, n = counterB.length.
     * Space Complexity: O(m + n) to store the result list.
     */
    public static List<Integer> mergeTokens(int[] counterA, int[] counterB) {
        List<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;

        while (i < counterA.length && j < counterB.length) {
            if (counterA[i] <= counterB[j]) {
                merged.add(counterA[i]);
                i++;
            } else {
                merged.add(counterB[j]);
                j++;
            }
        }

        while (i < counterA.length) {
            merged.add(counterA[i]);
            i++;
        }

        while (j < counterB.length) {
            merged.add(counterB[j]);
            j++;
        }

        return merged;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 2: Merging Two Token Queues ===");

        // Sample 1
        int[] counterA1 = {3, 8, 15, 20};
        int[] counterB1 = {5, 8, 12};
        List<Integer> result1 = mergeTokens(counterA1, counterB1);
        System.out.println("Sample 1:");
        System.out.println("Counter A: " + Arrays.toString(counterA1));
        System.out.println("Counter B: " + Arrays.toString(counterB1));
        System.out.println("Expected:  [3, 5, 8, 8, 12, 15, 20]");
        System.out.println("Actual:    " + result1);

        // Sample 2
        int[] counterA2 = {};
        int[] counterB2 = {4, 9};
        List<Integer> result2 = mergeTokens(counterA2, counterB2);
        System.out.println("\nSample 2:");
        System.out.println("Counter A: " + Arrays.toString(counterA2));
        System.out.println("Counter B: " + Arrays.toString(counterB2));
        System.out.println("Expected:  [4, 9]");
        System.out.println("Actual:    " + result2);

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.println("Enter sorted space-separated integers for Counter A (or empty):");
                String lineA = scanner.nextLine().trim();
                System.out.println("Enter sorted space-separated integers for Counter B (or empty):");
                String lineB = scanner.nextLine().trim();

                int[] arrA = lineA.isEmpty() ? new int[0] : Arrays.stream(lineA.split("\\s+")).mapToInt(Integer::parseInt).toArray();
                int[] arrB = lineB.isEmpty() ? new int[0] : Arrays.stream(lineB.split("\\s+")).mapToInt(Integer::parseInt).toArray();

                System.out.println("Merged result: " + mergeTokens(arrA, arrB));
            }
        }
    }
}
