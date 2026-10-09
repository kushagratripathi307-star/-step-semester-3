package Week8.Assignment.P02_AssignmentSubmissionPortal;

import java.util.Scanner;

enum SubmissionStatus {
    SUBMITTED("Submitted"),
    GRADED("Graded");

    private final String displayName;

    SubmissionStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

abstract class Assignment {
    private String title;
    private int maxMarks;
    private int dueDay; // Represented as day of month for easy comparison

    public Assignment(String title, int maxMarks, int dueDay) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDay = dueDay;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public int getDueDay() {
        return dueDay;
    }

    public abstract double getDailyPenaltyRate();

    public int calculateLateDays(int submissionDay) {
        return Math.max(0, submissionDay - dueDay);
    }

    public double calculatePenaltyPercentage(int submissionDay) {
        int lateDays = calculateLateDays(submissionDay);
        return lateDays * getDailyPenaltyRate();
    }
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    @Override
    public double getDailyPenaltyRate() {
        return 0.10; // 10% per day late
    }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    @Override
    public double getDailyPenaltyRate() {
        return 0.20; // 20% per day late
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Submission {
    private Student student;
    private Assignment assignment;
    private int submissionDay;
    private SubmissionStatus status;
    private double finalMarks;

    public Submission(Student student, Assignment assignment, int submissionDay) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDay = submissionDay;
        this.status = SubmissionStatus.SUBMITTED;

        int lateDays = assignment.calculateLateDays(submissionDay);
        String timing = lateDays == 0 ? "on time" : lateDays + " days late";
        System.out.printf("%s's submission for '%s' received (%s). Status: %s.%n",
                student.getName(), assignment.getTitle(), timing, status.getDisplayName());
    }

    public boolean resubmit(int newSubmissionDay) {
        if (status == SubmissionStatus.GRADED) {
            System.out.printf("Cannot resubmit: '%s' has already been graded.%n", assignment.getTitle());
            return false;
        }
        this.submissionDay = newSubmissionDay;
        this.status = SubmissionStatus.SUBMITTED;
        int lateDays = assignment.calculateLateDays(newSubmissionDay);
        String timing = lateDays == 0 ? "on time" : lateDays + " days late";
        System.out.printf("%s's resubmission for '%s' received (%s). Status: %s.%n",
                student.getName(), assignment.getTitle(), timing, status.getDisplayName());
        return true;
    }

    public void grade(double awardedMarks) {
        if (status == SubmissionStatus.GRADED) {
            System.out.println("Submission is already graded.");
            return;
        }
        double penaltyPercent = assignment.calculatePenaltyPercentage(submissionDay);
        this.finalMarks = awardedMarks * (1.0 - penaltyPercent);
        this.status = SubmissionStatus.GRADED;

        if (penaltyPercent > 0) {
            int penaltyDisplay = (int) Math.round(penaltyPercent * 100);
            System.out.printf("%s graded: %d/%d after %d%% late penalty. Status: %s.%n",
                    student.getName(), (int) Math.round(finalMarks), assignment.getMaxMarks(), penaltyDisplay, status.getDisplayName());
        } else {
            System.out.printf("%s graded: %d/%d. Status: %s.%n",
                    student.getName(), (int) Math.round(finalMarks), assignment.getMaxMarks(), status.getDisplayName());
        }
    }

    public Student getStudent() {
        return student;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public double getFinalMarks() {
        return finalMarks;
    }
}

public class AssignmentSubmissionPortal {
    public static void main(String[] args) {
        System.out.println("=== Problem 2: The Assignment Submission Portal ===");

        Assignment linkedListLab = new CodingAssignment("Linked List Lab", 50, 10);
        Assignment designEssay = new WrittenAssignment("Design Essay", 50, 12);

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        // Asha submits 'Linked List Lab' on Mar 10
        Submission ashaSub = new Submission(asha, linkedListLab, 10);

        // Ravi submits 'Design Essay' on Mar 14
        Submission raviSub = new Submission(ravi, designEssay, 14);

        // Faculty awards Asha 45 marks
        ashaSub.grade(45);

        // Faculty awards Ravi 40 marks
        raviSub.grade(40);

        // Asha attempts to resubmit 'Linked List Lab'
        ashaSub.resubmit(15);

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter student name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Enter submission day of month (e.g. 11 for Mar 11): ");
                int day = scanner.hasNextInt() ? scanner.nextInt() : 10;
                Submission userSub = new Submission(new Student(name), linkedListLab, day);
                System.out.print("Enter awarded marks (out of 50): ");
                double marks = scanner.hasNextDouble() ? scanner.nextDouble() : 45.0;
                userSub.grade(marks);
            }
        }
    }
}
