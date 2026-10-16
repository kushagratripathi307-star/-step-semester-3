import java.util.Scanner;

/**
 * Week 10 - Category B - Coding Assignment
 * Problem 1: Priority Token Insertion
 *
 * Task:
 * Implement addFirst, addLast, and insertAt(int index, int token) (0-based)
 * on a singly linked list and print the list after all operations.
 * Uses a custom Node class with data and next fields; does not use java.util.LinkedList.
 *
 * Time Complexities:
 * - addFirst(int token): O(1) - Simply points new node's next to current head and updates head.
 * - addLast(int token): O(n) - Traverses to the end of the list to link the new node.
 * - insertAt(int index, int token): O(n) - Traverses up to index-1 to insert the node.
 * Space Complexity:
 * - O(1) auxiliary space beyond the memory needed for the newly created node.
 */
public class PriorityTokenInsertion {

    // Custom Node class as required (data and next fields)
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

    // Singly Linked List Implementation
    public static class SinglyLinkedList {
        private Node head;
        private int size;

        public SinglyLinkedList() {
            this.head = null;
            this.size = 0;
        }

        public Node getHead() {
            return head;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return head == null;
        }

        /**
         * Adds a token at the front of the list (priority token).
         * Time Complexity: O(1)
         */
        public void addFirst(int token) {
            Node newNode = new Node(token);
            newNode.next = head;
            head = newNode;
            size++;
        }

        /**
         * Adds a token at the end of the list (normal token).
         * Time Complexity: O(n) where n is current list size.
         */
        public void addLast(int token) {
            Node newNode = new Node(token);
            if (head == null) {
                head = newNode;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
            size++;
        }

        /**
         * Inserts a token at the specified 0-based index (rescheduled token).
         * Time Complexity: O(n) where n is current list size.
         */
        public void insertAt(int index, int token) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Invalid index: " + index + ", valid range: [0, " + size + "]");
            }

            if (index == 0) {
                addFirst(token);
                return;
            }

            if (index == size) {
                addLast(token);
                return;
            }

            // Walk to the node just before the index (index - 1)
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            // Insert new node: set new node's next first, then link current to it
            Node newNode = new Node(token);
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }

        /**
         * Converts list to display format: "100 -> 101 -> 150 -> 102 -> 103 -> 104 -> null"
         */
        public String toDisplayString() {
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

        public void printList() {
            System.out.println(toDisplayString());
        }
    }

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("Week 10 - Assignment Problem 1: Priority Token Insertion");
        System.out.println("==================================================");

        // Run and verify the sample case from the PDF
        SinglyLinkedList list = new SinglyLinkedList();
        // start = 101 -> 102 -> 103
        list.addLast(101);
        list.addLast(102);
        list.addLast(103);
        System.out.println("Initial List: " + list.toDisplayString());

        System.out.println("\nApplying operations:");
        System.out.println("1. addFirst(100)");
        list.addFirst(100);
        System.out.println("   List state: " + list.toDisplayString());

        System.out.println("2. addLast(104)");
        list.addLast(104);
        System.out.println("   List state: " + list.toDisplayString());

        System.out.println("3. insertAt(2, 150)");
        list.insertAt(2, 150);
        System.out.println("   List state: " + list.toDisplayString());

        System.out.println("\nExpected Output: 100 -> 101 -> 150 -> 102 -> 103 -> 104 -> null");
        System.out.println("Actual Output:   " + list.toDisplayString());

        System.out.println("\nTime Complexity Analysis:");
        System.out.println("- addFirst(token):             O(1) (Direct head reference update)");
        System.out.println("- addLast(token):              O(n) (Traverses list of length n)");
        System.out.println("- insertAt(index, token):      O(n) (Traverses up to index-1)");
        System.out.println("- Additional Space Complexity: O(1)");

        // Interactive Scanner demonstration if input is available
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nRun custom interactive test? (y/n): ");
        if (scanner.hasNextLine()) {
            String choice = scanner.nextLine().trim();
            if (choice.equalsIgnoreCase("y") || choice.equalsIgnoreCase("yes")) {
                SinglyLinkedList customList = new SinglyLinkedList();
                System.out.print("Enter number of initial elements: ");
                if (scanner.hasNextInt()) {
                    int count = scanner.nextInt();
                    System.out.print("Enter " + count + " integer values: ");
                    for (int i = 0; i < count; i++) {
                        if (scanner.hasNextInt()) {
                            customList.addLast(scanner.nextInt());
                        }
                    }
                    System.out.println("Custom List: " + customList.toDisplayString());

                    System.out.print("Enter number of operations: ");
                    if (scanner.hasNextInt()) {
                        int numOps = scanner.nextInt();
                        for (int op = 0; op < numOps; op++) {
                            System.out.print("Enter op (addFirst <val> | addLast <val> | insertAt <idx> <val>): ");
                            String opType = scanner.next();
                            if (opType.equalsIgnoreCase("addFirst")) {
                                int val = scanner.nextInt();
                                customList.addFirst(val);
                            } else if (opType.equalsIgnoreCase("addLast")) {
                                int val = scanner.nextInt();
                                customList.addLast(val);
                            } else if (opType.equalsIgnoreCase("insertAt")) {
                                int idx = scanner.nextInt();
                                int val = scanner.nextInt();
                                customList.insertAt(idx, val);
                            }
                            System.out.println("Current list: " + customList.toDisplayString());
                        }
                    }
                }
            }
        }
    }
}
