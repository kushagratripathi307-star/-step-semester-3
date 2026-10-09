package Week9.Practice.P01_LibraryCatalogLookup;

import java.util.*;

public class LibraryCatalogLookup {

    public static class BookRecord {
        private final String isbn;
        private final String title;

        public BookRecord(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "(\"" + isbn + "\", \"" + title + "\")";
        }
    }

    /**
     * Efficiently looks up a book title by ISBN using Binary Search on the sorted catalog.
     * Time Complexity: O(log N) where N is number of books in the catalog.
     * Space Complexity: O(1) auxiliary space.
     */
    public static String findBook(List<BookRecord> catalog, String targetIsbn) {
        if (catalog == null || catalog.isEmpty() || targetIsbn == null) {
            return "Not Found";
        }

        int low = 0;
        int high = catalog.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            BookRecord midBook = catalog.get(mid);
            int cmp = midBook.getIsbn().compareTo(targetIsbn);

            if (cmp == 0) {
                return midBook.getTitle();
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 1: Library Catalog Lookup ===");

        List<BookRecord> catalog = List.of(
                new BookRecord("0001112223", "Introduction to Algebra"),
                new BookRecord("0002223334", "Beginning Python"),
                new BookRecord("0003334445", "Classic Mythology"),
                new BookRecord("0004445556", "Data and Society"),
                new BookRecord("0005556667", "European History")
        );

        System.out.println("Catalog: " + catalog);

        // Example 1
        String target1 = "0003334445";
        String res1 = findBook(catalog, target1);
        System.out.println("\nSearch: " + target1);
        System.out.println("Expected: \"Classic Mythology\"");
        System.out.println("Actual:   \"" + res1 + "\"");

        // Example 2
        String target2 = "0009998887";
        String res2 = findBook(catalog, target2);
        System.out.println("\nSearch: " + target2);
        System.out.println("Expected: \"Not Found\"");
        System.out.println("Actual:   \"" + res2 + "\"");

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter ISBN to look up: ");
                String isbn = scanner.nextLine().trim();
                System.out.println("Lookup result: " + findBook(catalog, isbn));
            }
        }
    }
}
