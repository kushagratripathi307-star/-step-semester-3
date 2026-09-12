package Week6.Practice.P03_StudentDiscountFineLedger;

import java.util.Arrays;
import java.util.Scanner;

class LibraryMember {
    private String memberId;
    private int borrowLimit;
    private int[] fineHistory;
    private int fineCount;

    public LibraryMember(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.fineHistory = new int[10];
        this.fineCount = 0;
    }

    protected void chargeFine(int amount) {
        if (fineCount < fineHistory.length) {
            fineHistory[fineCount++] = amount;
        }
    }

    public int[] getFineHistory() {
        return Arrays.copyOf(fineHistory, fineCount);
    }

    public int getTotalFine() {
        int total = 0;
        for (int i = 0; i < fineCount; i++) {
            total += fineHistory[i];
        }
        return total;
    }

    public String getMemberId() {
        return memberId;
    }
}

class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }

    public String getCourse() {
        return course;
    }
}

public class FineLedger {

    public static void main(String[] args) {
        System.out.println("=== Problem 3 Demo ===");

        // Example 1: Student discount on fine
        StudentMember s = new StudentMember("STU5", 3, "CSE");
        s.chargeFine(100);
        System.out.println("Total fine after charging 100 to StudentMember: " + s.getTotalFine());

        // Example 2: Defensive copying test
        int[] history = s.getFineHistory();
        System.out.println("Original Fine History: " + Arrays.toString(history));
        history[0] = 999; // Attempting to tamper
        System.out.println("Tampered external array[0] = 999");
        System.out.println("Internal Fine History after tamper attempt: " + Arrays.toString(s.getFineHistory()));

        // Interactive Scanner mode
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter original fine amount: ");
                int fine = Integer.parseInt(scanner.nextLine());
                StudentMember userStudent = new StudentMember("USER1", 3, "ECE");
                userStudent.chargeFine(fine);
                System.out.println("Discounted Fine Charged: " + userStudent.getTotalFine());
                System.out.println("Fine History: " + Arrays.toString(userStudent.getFineHistory()));
            }
        }
    }
}
