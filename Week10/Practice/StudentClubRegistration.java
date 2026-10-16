import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

/**
 * Week 10 - Category B - Practice Problem 3
 * Problem: Student Club Registration Management
 *
 * Task:
 * Develop a student club registration system that stores members by their unique roll number.
 * Ensure that when checking if a student is already registered, only the roll number is
 * considered for uniqueness (overriding equals() and hashCode() based solely on rollNumber).
 *
 * Operations:
 * - ADD <RollNumber> <Name>: Prints "Added" if new, "duplicate rejected" if present.
 * - End of ADD operations: Prints "member count <count>".
 * - CONTAINS <RollNumber> <Name>: Prints "contains: true" or "contains: false".
 */
public class StudentClubRegistration {

    // Student entity with rollNumber-based equality
    public static class Student {
        private final String rollNumber;
        private final String name;

        public Student(String rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }

        public String getRollNumber() {
            return rollNumber;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            // Equality based STRICTLY on rollNumber as required
            return Objects.equals(rollNumber, student.rollNumber);
        }

        @Override
        public int hashCode() {
            // HashCode based STRICTLY on rollNumber as required
            return Objects.hash(rollNumber);
        }

        @Override
        public String toString() {
            return "Student[rollNumber=" + rollNumber + ", name=" + name + "]";
        }
    }

    // Club Registry
    public static class ClubRegistry {
        private final Set<Student> members = new HashSet<>();
        private boolean memberCountPrinted = false;

        public void addStudent(String rollNumber, String name) {
            Student s = new Student(rollNumber, name);
            if (members.contains(s)) {
                System.out.println("duplicate rejected");
            } else {
                members.add(s);
                System.out.println("Added");
            }
        }

        public void printMemberCountIfNeeded() {
            if (!memberCountPrinted) {
                System.out.println("member count " + members.size());
                memberCountPrinted = true;
            }
        }

        public void containsStudent(String rollNumber, String name) {
            printMemberCountIfNeeded();
            Student s = new Student(rollNumber, name);
            boolean exists = members.contains(s);
            System.out.println("contains: " + exists);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            ClubRegistry registry = new ClubRegistry();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() || line.equalsIgnoreCase("EXIT")) {
                    break;
                }
                String[] parts = line.split("\\s+");
                String command = parts[0];

                if (command.equalsIgnoreCase("ADD")) {
                    if (parts.length >= 3) {
                        registry.addStudent(parts[1], parts[2]);
                    }
                } else if (command.equalsIgnoreCase("CONTAINS")) {
                    if (parts.length >= 3) {
                        registry.containsStudent(parts[1], parts[2]);
                    }
                }
            }
            registry.printMemberCountIfNeeded();
        } else {
            // Run exact demo from the PDF
            System.out.println("=== Student Club Registration Management Demo ===");
            ClubRegistry registry = new ClubRegistry();

            // Sample operations
            registry.addStudent("21CS01", "Asha");
            registry.addStudent("21CS01", "Asha");
            registry.addStudent("21CS02", "Ravi");

            registry.containsStudent("21CS02", "Ravi");
            registry.containsStudent("21CS03", "Priya");
        }
    }
}
