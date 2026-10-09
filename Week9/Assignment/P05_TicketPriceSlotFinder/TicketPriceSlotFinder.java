package Week9.Assignment.P05_TicketPriceSlotFinder;

import java.util.Arrays;
import java.util.Scanner;

public class TicketPriceSlotFinder {

    /**
     * Finds the index of newPrice if present, or the index where it should be inserted
     * to maintain sorted order using Binary Search.
     * Time Complexity: O(log n) where n = prices.length.
     * Space Complexity: O(1) auxiliary space.
     */
    public static int findSlot(int[] prices, int newPrice) {
        if (prices == null || prices.length == 0) {
            return 0;
        }

        int low = 0;
        int high = prices.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (prices[mid] == newPrice) {
                return mid;
            } else if (prices[mid] < newPrice) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 5: Ticket Price Slot Finder ===");

        int[] prices = {120, 150, 200, 260};

        System.out.println("Prices Array: " + Arrays.toString(prices));

        // Sample 1
        int newPrice1 = 150;
        int slot1 = findSlot(prices, newPrice1);
        System.out.println("Sample 1 (newPrice = 150) -> Expected: 1, Actual: " + slot1);

        // Sample 2
        int newPrice2 = 210;
        int slot2 = findSlot(prices, newPrice2);
        System.out.println("Sample 2 (newPrice = 210) -> Expected: 3, Actual: " + slot2);

        // Sample 3
        int newPrice3 = 300;
        int slot3 = findSlot(prices, newPrice3);
        System.out.println("Sample 3 (newPrice = 300) -> Expected: 4, Actual: " + slot3);

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter new price to query: ");
                if (scanner.hasNextInt()) {
                    int p = scanner.nextInt();
                    System.out.println("Slot index: " + findSlot(prices, p));
                }
            }
        }
    }
}
