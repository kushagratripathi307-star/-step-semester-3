package Week9.Practice.P04_PairWithTargetSumUnsortedArray;

import java.util.*;

public class PairWithTargetSumUnsortedArray {

    /**
     * Approach 1: Hash-Based Complement Lookup
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static boolean hasPairWithSumHash(int[] nums, int target) {
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
     * Approach 2: Sorting + Two-Pointer Technique
     * Time Complexity: O(N log N) for sorting + O(N) for two pointers = O(N log N).
     * Space Complexity: O(N) or O(1) depending on in-place sort.
     */
    public static boolean hasPairWithSumTwoPointers(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);

        int left = 0;
        int right = copy.length - 1;

        while (left < right) {
            int sum = copy[left] + copy[right];
            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 4: Pair With Target Sum (Unsorted Array) ===");

        // Sample 1
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Sample 1: " + Arrays.toString(nums1) + ", Target: " + target1);
        System.out.println("Expected: true (because 2 + 7 = 9)");
        System.out.println("Actual (Hash Approach):        " + hasPairWithSumHash(nums1, target1));
        System.out.println("Actual (Two-Pointer Approach): " + hasPairWithSumTwoPointers(nums1, target1));

        // Sample 2
        int[] nums2 = {3, 4, 6};
        int target2 = 20;
        System.out.println("\nSample 2: " + Arrays.toString(nums2) + ", Target: " + target2);
        System.out.println("Expected: false");
        System.out.println("Actual (Hash Approach):        " + hasPairWithSumHash(nums2, target2));
        System.out.println("Actual (Two-Pointer Approach): " + hasPairWithSumTwoPointers(nums2, target2));

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
                System.out.println("Two-pointer check result: " + hasPairWithSumTwoPointers(customArr, customTarget));
            }
        }
    }
}
