import java.util.Scanner;

/**
 * Week 10 - Category B - Coding Assignment
 * Problem 2: Cancelled Orders Cleanup
 *
 * Task:
 * Implement Node removeAll(Node head, int code) that removes every node with the given value
 * and returns the (possibly new) head.
 * Uses a custom Node class with data and next fields; does not use java.util.LinkedList.
 *
 * Time Complexity:
 * - O(n) where n is the number of nodes in the linked list (single traversal).
 * Space Complexity:
 * - O(1) auxiliary space (modifications are performed in-place by adjusting references).
 */
public class CancelledOrdersCleanup {

    // Custom Node class as required
    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    /**
     * Removes every node with data equal to code and returns the new head.
     * Algorithm:
     * 1. Remove matching nodes from the front by shifting head forward.
     * 2. Traverse the remainder of the list using a current pointer, skipping any next node
     *    whose data matches the target code.
     *
     * @param head Head node of the singly linked list
     * @param code The order code to remove
     * @return New head reference after removal
     */
    public static Node removeAll(Node head, int code) {
        // Step 1: Remove matching nodes from the front
        while (head != null && head.data == code) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        // Step 2: Walk the list and bypass matching nodes
        Node current = head;
        while (current != null && current.next != null) {
            if (current.next.data == code) {
                current.next = current.next.next; // skip node
            } else {
                current = current.next;
            }
        }

        return head;
    }

    /**
     * Utility method to create a linked list from an array of integers.
     */
    public static Node createList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new Node(values[i]);
            current = current.next;
        }
        return head;
    }

    /**
     * Utility method to format a linked list as "val1 -> val2 -> ... -> null".
     */
    public static String toDisplayString(Node head) {
        if (head == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current.data).append(" -> ");
            current = current.next;
        }
        sb.append("null");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Week 10 - Assignment Problem 2: Cancelled Orders Cleanup");
        System.out.println("==================================================");

        // Verification on Sample 1
        System.out.println("--- Test Case 1 (Sample 1 from PDF) ---");
        int[] sample1Values = {5, 3, 5, 8, 5};
        int code1 = 5;
        Node list1 = createList(sample1Values);
        System.out.println("Input List:    " + toDisplayString(list1));
        System.out.println("Code to Purge: " + code1);
        Node result1 = removeAll(list1, code1);
        System.out.println("Expected:      3 -> 8 -> null");
        System.out.println("Actual:        " + toDisplayString(result1));

        // Verification on Sample 2
        System.out.println("\n--- Test Case 2 (Sample 2 from PDF) ---");
        int[] sample2Values = {5, 5, 5};
        int code2 = 5;
        Node list2 = createList(sample2Values);
        System.out.println("Input List:    " + toDisplayString(list2));
        System.out.println("Code to Purge: " + code2);
        Node result2 = removeAll(list2, code2);
        System.out.println("Expected:      null");
        System.out.println("Actual:        " + toDisplayString(result2));

        // Edge case: empty list
        System.out.println("\n--- Test Case 3 (Edge Case: Empty List) ---");
        Node emptyList = null;
        System.out.println("Input List:    " + toDisplayString(emptyList));
        System.out.println("Code to Purge: 10");
        Node result3 = removeAll(emptyList, 10);
        System.out.println("Expected:      null");
        System.out.println("Actual:        " + toDisplayString(result3));

        // Edge case: target code not present
        System.out.println("\n--- Test Case 4 (Code Not Present) ---");
        int[] sample4Values = {1, 2, 3};
        Node list4 = createList(sample4Values);
        System.out.println("Input List:    " + toDisplayString(list4));
        System.out.println("Code to Purge: 99");
        Node result4 = removeAll(list4, 99);
        System.out.println("Expected:      1 -> 2 -> 3 -> null");
        System.out.println("Actual:        " + toDisplayString(result4));

        System.out.println("\nComplexity Analysis:");
        System.out.println("- Time Complexity:             O(n) where n is list length (single linear pass)");
        System.out.println("- Additional Space Complexity: O(1) (in-place reference re-linking)");

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nRun custom interactive test? (y/n): ");
        if (scanner.hasNextLine()) {
            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                System.out.print("Enter number of order codes: ");
                if (scanner.hasNextInt()) {
                    int n = scanner.nextInt();
                    int[] userOrders = new int[n];
                    System.out.print("Enter " + n + " order codes: ");
                    for (int i = 0; i < n; i++) {
                        if (scanner.hasNextInt()) {
                            userOrders[i] = scanner.nextInt();
                        }
                    }
                    System.out.print("Enter code to cancel: ");
                    if (scanner.hasNextInt()) {
                        int code = scanner.nextInt();
                        Node customList = createList(userOrders);
                        System.out.println("Original orders: " + toDisplayString(customList));
                        Node cleaned = removeAll(customList, code);
                        System.out.println("Cleaned orders:  " + toDisplayString(cleaned));
                    }
                }
            }
        }
    }
}
