package Week6.Practice.P02_ThreeBranchesMembershipTree;

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

    public String displayInfo() {
        return "General Member | Books Borrowed: " + booksBorrowed;
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
        return "Student Member | Course: " + course + " | Books Borrowed: " + getBooksBorrowed();
    }
}

class HonorsStudentMember extends StudentMember {
    private int bonusLimit;

    public HonorsStudentMember(String memberId, int borrowLimit, String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    public int getBonusLimit() {
        return bonusLimit;
    }

    @Override
    public String displayInfo() {
        return "Honors Student Member | Course: " + getCourse() + " | Bonus Limit: " + bonusLimit + " | Books Borrowed: " + getBooksBorrowed();
    }
}

class FacultyMember extends LibraryMember {
    private String department;

    public FacultyMember(String memberId, int borrowLimit, String department) {
        super(memberId, borrowLimit);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String displayInfo() {
        return "Faculty Member | Department: " + department + " | Books Borrowed: " + getBooksBorrowed();
    }
}

public class MembershipTree {

    public static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof StudentMember) {
            return "Direct subclass (2 generations deep)";
        } else {
            return "Base class";
        }
    }

    public static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;
        if (members != null) {
            for (LibraryMember member : members) {
                if (member != null) {
                    total += member.getBooksBorrowed();
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 2 Demo ===");

        LibraryMember m1 = new LibraryMember("STU1", 3);
        StudentMember m2 = new StudentMember("STU2", 3, "CSE");
        HonorsStudentMember m3 = new HonorsStudentMember("STU3", 3, "ECE", 2);
        FacultyMember m4 = new FacultyMember("STU4", 5, "Physics");

        System.out.println(m1.displayInfo());
        System.out.println(m2.displayInfo());
        System.out.println(m3.displayInfo());
        System.out.println(m4.displayInfo());

        System.out.println("\n--- Classify Generation ---");
        System.out.println("classifyGeneration(honorsMember): " + classifyGeneration(m3));
        System.out.println("classifyGeneration(facultyMember): " + classifyGeneration(m4));

        m2.borrowBook();
        m2.borrowBook();
        m3.borrowBook();
        m4.borrowBook();
        m4.borrowBook();
        m4.borrowBook();

        LibraryMember[] mixedList = {m2, m3, m4};
        System.out.println("\nTotal Books Borrowed across mixed list: " + getTotalBooksBorrowed(mixedList));

        // Interactive Scanner mode
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter Faculty Department: ");
                String dept = scanner.nextLine();
                FacultyMember fm = new FacultyMember("FAC01", 5, dept);
                System.out.println("Created: " + fm.displayInfo());
                System.out.println("Classification: " + classifyGeneration(fm));
            }
        }
    }
}
