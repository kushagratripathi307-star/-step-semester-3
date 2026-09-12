package Week6.Practice.P05_MembershipNumbersRenewalCodesAudit;

import java.util.Scanner;

class LibraryMember {
    private static int counter = 100;
    private static int enrolledCount = 0;

    public final String memberNumber;
    private int borrowLimit;
    private int booksBorrowed;
    private String lastBorrowedGenre;

    public LibraryMember(int borrowLimit) {
        counter++;
        enrolledCount++;
        this.memberNumber = "LIB-" + counter;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public static int getMembersEnrolled() {
        return enrolledCount;
    }

    public static void resetCounter() {
        counter = 100;
        enrolledCount = 0;
    }

    public static boolean isValidRenewalCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        return code.charAt(0) == 'R'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3));
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public void borrowBook(String genre) {
        this.lastBorrowedGenre = genre;
        borrowBook();
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String getLastBorrowedGenre() {
        return lastBorrowedGenre;
    }
}

class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(int borrowLimit, String department) {
        super(borrowLimit);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }
}

public class MembershipAudit {

    public static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        if (members != null) {
            for (LibraryMember member : members) {
                if (member == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (member instanceof FacultyMember) {
                        faculty++;
                    } else {
                        regular++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + faculty + " faculty | " + regular + " regular";
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 5 Demo ===");

        // Test 1: Member number and counter
        LibraryMember m1 = new LibraryMember(3);
        System.out.println("m1.memberNumber: " + m1.memberNumber);
        System.out.println("LibraryMember.getMembersEnrolled(): " + LibraryMember.getMembersEnrolled());

        // Test 2: Renewal Code Validation
        System.out.println("\n--- Renewal Code Validation ---");
        System.out.println("isValidRenewalCode(\"R12A\"): " + LibraryMember.isValidRenewalCode("R12A"));
        System.out.println("isValidRenewalCode(\"R1A\"):  " + LibraryMember.isValidRenewalCode("R1A"));
        System.out.println("isValidRenewalCode(\"X12A\"): " + LibraryMember.isValidRenewalCode("X12A"));

        // Test 3: Overloaded borrowBook
        System.out.println("\n--- Overloaded borrowBook ---");
        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println("m1.getBooksBorrowed(): " + m1.getBooksBorrowed());

        // Test 4: Nightly Circulation Audit
        System.out.println("\n--- Nightly Circulation Audit ---");
        LibraryMember[] auditBatch = {
            new FacultyMember(5, "Physics"),
            null,
            new LibraryMember(3)
        };
        System.out.println("Audit Result: " + processNightlyAudit(auditBatch));

        // Interactive Scanner mode
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter renewal code to validate: ");
                String code = scanner.nextLine();
                System.out.println("Validation result: " + LibraryMember.isValidRenewalCode(code));
            }
        }
    }
}
