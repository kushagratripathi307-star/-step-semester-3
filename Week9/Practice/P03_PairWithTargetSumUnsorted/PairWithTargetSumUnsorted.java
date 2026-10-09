package Week9.Practice.P03_PairWithTargetSumUnsorted;

import java.util.*;

public class PairWithTargetSumUnsorted {

    /**
     * Approach 1: Optimal Hash Set Lookup
     * Checks if there are any two distinct elements that add up to target.
     * Time Complexity: O(N) where N = nums.length.
     * Space Complexity: O(N) to store seen elements.
     */
    public static boolean hasPairWithSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        Set<Integer> seen = new HashSet<>();
        for (int num : nums) {
            int complement = target - num;
            if (seen.contains(complement)) {
                return true;
            }
            seen.add(num);
        }

        return false;
    }

    /**
     * Approach 2: Brute-Force Pair Comparison
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     */
    public static boolean hasPairWithSumBruteForce(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 3: Pair With Target Sum (Unsorted) ===");

        // Example 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Nums: " + Arrays.toString(nums1) + ", Target: " + target1);
        System.out.println("Expected: true (because 2 + 7 = 9)");
        System.out.println("Actual (HashSet):     " + hasPairWithSum(nums1, target1));
        System.out.println("Actual (BruteForce):  " + hasPairWithSumBruteForce(nums1, target1));

        // Example 2
        int[] nums2 = {3, 4, 6};
        int target2 = 20;
        System.out.println("\nNums: " + Arrays.toString(nums2) + ", Target: " + target2);
        System.out.println("Expected: false");
        System.out.println("Actual (HashSet):     " + hasPairWithSum(nums2, target2));
        System.out.println("Actual (BruteForce):  " + hasPairWithSumBruteForce(nums2, target2));

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.println("Enter space-separated integers:");
                String line = scanner.nextLine().trim();
                int[] customArr = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();
                System.out.print("Enter target sum: ");
                int customTarget = scanner.nextInt();
                System.out.println("Has pair: " + hasPairWithSum(customArr, customTarget));
            }
        }
    }
}
