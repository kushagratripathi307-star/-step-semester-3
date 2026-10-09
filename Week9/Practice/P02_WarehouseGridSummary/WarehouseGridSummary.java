package Week9.Practice.P02_WarehouseGridSummary;

import java.util.Arrays;
import java.util.Scanner;

public class WarehouseGridSummary {

    public static class GridSummaryResult {
        private final int totalItems;
        private final int maxRow;
        private final int maxCol;

        public GridSummaryResult(int totalItems, int maxRow, int maxCol) {
            this.totalItems = totalItems;
            this.maxRow = maxRow;
            this.maxCol = maxCol;
        }

        public int getTotalItems() {
            return totalItems;
        }

        public int getMaxRow() {
            return maxRow;
        }

        public int getMaxCol() {
            return maxCol;
        }

        @Override
        public String toString() {
            return "(" + totalItems + ", (" + maxRow + ", " + maxCol + "))";
        }
    }

    /**
     * Calculates total items and the (row, col) coordinates of the bin with the maximum count.
     * Ties are broken by picking the first occurrence in row-major scan order.
     * Time Complexity: O(m * n) where m = grid.length, n = grid[0].length.
     * Space Complexity: O(1) auxiliary space.
     */
    public static GridSummaryResult warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return new GridSummaryResult(0, -1, -1);
        }

        int totalItems = 0;
        int maxVal = -1;
        int maxRow = 0;
        int maxCol = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                int count = grid[r][c];
                totalItems += count;
                if (count > maxVal) {
                    maxVal = count;
                    maxRow = r;
                    maxCol = c;
                }
            }
        }

        return new GridSummaryResult(totalItems, maxRow, maxCol);
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 2: Warehouse Grid Summary ===");

        int[][] grid = {
                {4, 9, 2},
                {7, 1, 6},
                {3, 12, 5}
        };

        System.out.println("Warehouse Grid: " + Arrays.deepToString(grid));
        GridSummaryResult summary = warehouseSummary(grid);
        System.out.println("Expected Output: (49, (2, 1))");
        System.out.println("Actual Output:   " + summary);

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter rows: ");
                int m = scanner.hasNextInt() ? scanner.nextInt() : 3;
                System.out.print("Enter cols: ");
                int n = scanner.hasNextInt() ? scanner.nextInt() : 3;
                int[][] customGrid = new int[m][n];
                System.out.println("Enter items grid elements:");
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        if (scanner.hasNextInt()) {
                            customGrid[i][j] = scanner.nextInt();
                        }
                    }
                }
                System.out.println("Summary: " + warehouseSummary(customGrid));
            }
        }
    }
}
