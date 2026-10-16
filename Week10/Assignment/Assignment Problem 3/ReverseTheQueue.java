import java.util.Scanner;

/**
 * Week 10 - Category B - Coding Assignment
 * Problem 3: Reverse the Queue
 *
 * Task:
 * Reverse a singly linked list in place so that the last person in the queue is served first.
 * Implement Node reverse(Node head) and return the new head.
 * Uses a custom Node class with data and next fields; does not use java.util.LinkedList.
 * No new nodes may be created.
 *
 * Time Complexity:
 * - O(n) where n is the number of nodes in the list (each node visited once).
 * Space Complexity:
 * - O(1) auxiliary space (reverses next pointers in place using three pointer references).
 */
public class ReverseTheQueue {

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
     * Reverses the singly linked list in-place using three pointers (prev, current, next).
     * Strictly creates no new nodes.
     *
     * @param head Current head of the list
     * @return New head of the reversed list
     */
    public static Node reverse(Node head) {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.next; // store next pointer
            current.next = prev;          // reverse link
            prev = current;               // advance prev
            current = nextNode;           // advance current
        }

        return prev; // prev becomes the new head of the reversed list
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
        System.out.println("Week 10 - Assignment Problem 3: Reverse the Queue");
        System.out.println("==================================================");

        // Verification on Sample 1
        System.out.println("--- Test Case 1 (Sample 1 from PDF) ---");
        int[] sample1Values = {1, 2, 3, 4};
        Node list1 = createList(sample1Values);
        System.out.println("Original Queue: " + toDisplayString(list1));
        Node reversed1 = reverse(list1);
        System.out.println("Expected:       4 -> 3 -> 2 -> 1 -> null");
        System.out.println("Actual:         " + toDisplayString(reversed1));

        // Verification on Sample 2
        System.out.println("\n--- Test Case 2 (Sample 2 from PDF: Single Element) ---");
        int[] sample2Values = {9};
        Node list2 = createList(sample2Values);
        System.out.println("Original Queue: " + toDisplayString(list2));
        Node reversed2 = reverse(list2);
        System.out.println("Expected:       9 -> null");
        System.out.println("Actual:         " + toDisplayString(reversed2));

        // Edge case: empty list
        System.out.println("\n--- Test Case 3 (Edge Case: Empty List) ---");
        Node list3 = null;
        System.out.println("Original Queue: " + toDisplayString(list3));
        Node reversed3 = reverse(list3);
        System.out.println("Expected:       null");
        System.out.println("Actual:         " + toDisplayString(reversed3));

        System.out.println("\nComplexity Analysis:");
        System.out.println("- Time Complexity:             O(n) where n is list length");
        System.out.println("- Additional Space Complexity: O(1) (Strictly in-place, zero new nodes created)");

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nRun custom interactive test? (y/n): ");
        if (scanner.hasNextLine()) {
            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                System.out.print("Enter number of elements in queue: ");
                if (scanner.hasNextInt()) {
                    int n = scanner.nextInt();
                    int[] userQueue = new int[n];
                    System.out.print("Enter " + n + " elements: ");
                    for (int i = 0; i < n; i++) {
                        if (scanner.hasNextInt()) {
                            userQueue[i] = scanner.nextInt();
                        }
                    }
                    Node customList = createList(userQueue);
                    System.out.println("Original Queue: " + toDisplayString(customList));
                    Node reversedCustom = reverse(customList);
                    System.out.println("Reversed Queue: " + toDisplayString(reversedCustom));
                }
            }
        }
    }
}
