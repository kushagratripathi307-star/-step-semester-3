package Week9.Assignment.P01_ClassTopperFinder;

import java.util.Arrays;
import java.util.Scanner;

public class ClassTopperFinder {

    public static class TopperResult {
        private final int rowIndex;
        private final int total;

        public TopperResult(int rowIndex, int total) {
            this.rowIndex = rowIndex;
            this.total = total;
        }

        public int getRowIndex() {
            return rowIndex;
        }

        public int getTotal() {
            return total;
        }

        @Override
        public String toString() {
            return "(" + rowIndex + ", " + total + ")";
        }
    }

    /**
     * Finds the student with the highest total marks.
     * Time Complexity: O(m * n) where m is number of students and n is number of subjects.
     * Space Complexity: O(1) additional space.
     */
    public static TopperResult findTopper(int[][] marks) {
        if (marks == null || marks.length == 0) {
            return new TopperResult(-1, 0);
        }

        int bestIndex = 0;
        int maxTotal = -1;

        for (int i = 0; i < marks.length; i++) {
            int currentTotal = 0;
            for (int j = 0; j < marks[i].length; j++) {
                currentTotal += marks[i][j];
            }

            if (currentTotal > maxTotal) {
                maxTotal = currentTotal;
                bestIndex = i;
            }
        }

        return new TopperResult(bestIndex, maxTotal);
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 1: Class Topper Finder ===");

        int[][] marks = {
                {78, 85, 90}, // total: 253
                {88, 92, 79}, // total: 259
                {65, 70, 95}  // total: 230
        };

        System.out.println("Input Grid: " + Arrays.deepToString(marks));
        TopperResult result = findTopper(marks);
        System.out.println("Expected Output: (1, 259)");
        System.out.println("Actual Output:   " + result);

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter number of students (rows): ");
                int m = scanner.hasNextInt() ? scanner.nextInt() : 2;
                System.out.print("Enter number of subjects (cols): ");
                int n = scanner.hasNextInt() ? scanner.nextInt() : 3;
                int[][] customMarks = new int[m][n];
                System.out.println("Enter marks row by row:");
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < n; j++) {
                        if (scanner.hasNextInt()) {
                            customMarks[i][j] = scanner.nextInt();
                        }
                    }
                }
                System.out.println("Topper: " + findTopper(customMarks));
            }
        }
    }
}
