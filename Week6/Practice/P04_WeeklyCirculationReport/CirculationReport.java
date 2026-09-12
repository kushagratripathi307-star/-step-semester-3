package Week6.Practice.P04_WeeklyCirculationReport;

import java.util.Scanner;

class LibraryMember {
    private String memberId;
    private int borrowLimit;
    private int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public String getMemberId() {
        return memberId;
    }

    public String displayInfo() {
        return "General | Books: " + booksBorrowed;
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

    @Override
    public String displayInfo() {
        return "Student | Course: " + course + " | Books: " + getBooksBorrowed();
    }
}

public class CirculationReport {

    public static String batchPrint(LibraryMember[] members) {
        StringBuilder sb = new StringBuilder();
        if (members != null) {
            for (LibraryMember member : members) {
                if (member != null) {
                    sb.append(member.displayInfo());
                    if (member instanceof StudentMember) {
                        StudentMember sm = (StudentMember) member;
                        sb.append(" [Course via downcast: ").append(sm.getCourse()).append("]");
                    }
                    sb.append(" | ");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 4 Demo ===");

        LibraryMember[] batch = {
            new LibraryMember("LB5", 3),
            new StudentMember("STU6", 3, "ECE")
        };

        System.out.println("batchPrint Output:");
        System.out.println(batchPrint(batch));

        System.out.println("\n--- Downcast Safety Check ---");
        LibraryMember plain = new LibraryMember("LB6", 3);
        if (plain instanceof StudentMember) {
            StudentMember bad = (StudentMember) plain;
            System.out.println(bad.getCourse());
        } else {
            System.out.println("Safely avoided ClassCastException: plain LibraryMember is NOT a StudentMember");
        }

        // Interactive Scanner mode
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter Student Course: ");
                String course = scanner.nextLine();
                StudentMember sm = new StudentMember("STU99", 5, course);
                LibraryMember[] userBatch = {sm};
                System.out.println("Report: " + batchPrint(userBatch));
            }
        }
    }
}
