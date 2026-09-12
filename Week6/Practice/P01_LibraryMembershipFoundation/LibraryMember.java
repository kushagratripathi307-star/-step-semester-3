package Week6.Practice.P01_LibraryMembershipFoundation;

import java.util.Scanner;

public class LibraryMember {
    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getBorrowLimit() {
        return borrowLimit;
    }

    public static String enrollBatch(String[] memberIds, int borrowLimit) {
        int enrolled = 0;
        int rejected = 0;

        if (memberIds != null) {
            for (String id : memberIds) {
                try {
                    new LibraryMember(id, borrowLimit);
                    enrolled++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        return "Enrolled: " + enrolled + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 1 Demo ===");

        // Test 1: Single construction validation
        try {
            LibraryMember m1 = new LibraryMember("LB1", 3);
            System.out.println("Created: " + m1.getMemberId());
        } catch (IllegalArgumentException e) {
            System.out.println("new LibraryMember(\"LB1\", 3) -> " + e.getMessage());
        }

        // Test 2: StudentMember inheritance and borrowing
        StudentMember s = new StudentMember("STU10", 3, "CSE");
        s.borrowBook();
        s.borrowBook();
        System.out.println("StudentMember books borrowed: " + s.getBooksBorrowed());

        // Test 3: Batch Enrollment
        String[] batch = {"STU1", "LB1", "STU2", " ", "STU3"};
        System.out.println("enrollBatch result: " + enrollBatch(batch, 3));

        // Interactive Scanner mode
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter Member ID: ");
                String id = scanner.nextLine();
                System.out.print("Enter Borrow Limit: ");
                int limit = Integer.parseInt(scanner.nextLine());
                try {
                    LibraryMember userMember = new LibraryMember(id, limit);
                    System.out.println("Successfully created member: " + userMember.getMemberId());
                } catch (IllegalArgumentException e) {
                    System.out.println("Creation failed: " + e.getMessage());
                }
            }
        }
    }
}

class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }
}
