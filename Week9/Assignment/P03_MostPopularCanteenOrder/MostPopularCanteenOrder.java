package Week9.Assignment.P03_MostPopularCanteenOrder;

import java.util.*;

public class MostPopularCanteenOrder {

    public static class PopularItemResult {
        private final String item;
        private final int count;

        public PopularItemResult(String item, int count) {
            this.item = item;
            this.count = count;
        }

        public String getItem() {
            return item;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return "(\"" + item + "\", " + count + ")";
        }
    }

    /**
     * Finds the most popular canteen order.
     * Uses a HashMap to count frequencies in O(N) time and then iterates
     * through the original list to preserve the first-occurrence tie-breaker.
     * Time Complexity: O(N) where N is number of orders.
     * Space Complexity: O(U) where U is number of unique items.
     */
    public static PopularItemResult mostPopular(String[] orders) {
        if (orders == null || orders.length == 0) {
            return new PopularItemResult("", 0);
        }

        Map<String, Integer> freqMap = new HashMap<>();
        for (String order : orders) {
            freqMap.put(order, freqMap.getOrDefault(order, 0) + 1);
        }

        int maxCount = 0;
        String bestItem = "";

        for (String order : orders) {
            int count = freqMap.get(order);
            if (count > maxCount) {
                maxCount = count;
                bestItem = order;
            }
        }

        return new PopularItemResult(bestItem, maxCount);
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 3: Most Popular Canteen Order ===");

        // Sample 1
        String[] orders1 = {"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"};
        PopularItemResult res1 = mostPopular(orders1);
        System.out.println("Sample 1: " + Arrays.toString(orders1));
        System.out.println("Expected: (\"dosa\", 3)");
        System.out.println("Actual:   " + res1);

        // Sample 2
        String[] orders2 = {"tea", "coffee", "coffee", "tea"};
        PopularItemResult res2 = mostPopular(orders2);
        System.out.println("\nSample 2: " + Arrays.toString(orders2));
        System.out.println("Expected: (\"tea\", 2)");
        System.out.println("Actual:   " + res2);

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.println("Enter space-separated item names:");
                String line = scanner.nextLine().trim();
                String[] customOrders = line.split("\\s+");
                System.out.println("Most Popular: " + mostPopular(customOrders));
            }
        }
    }
}
