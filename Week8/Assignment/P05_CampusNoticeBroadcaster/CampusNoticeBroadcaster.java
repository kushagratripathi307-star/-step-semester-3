package Week8.Assignment.P05_CampusNoticeBroadcaster;

import java.util.*;

interface NotificationChannel {
    String getChannelName();
    void deliver(Student student, Notice notice);
}

class EmailChannel implements NotificationChannel {
    @Override
    public String getChannelName() {
        return "Email";
    }

    @Override
    public void deliver(Student student, Notice notice) {
        System.out.printf("[Email → %s] %s.%n", student.getName(), notice.getTitle());
    }
}

class AppChannel implements NotificationChannel {
    @Override
    public String getChannelName() {
        return "App";
    }

    @Override
    public void deliver(Student student, Notice notice) {
        System.out.printf("[App → %s] %s.%n", student.getName(), notice.getTitle());
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public String getChannelName() {
        return "SMS";
    }

    @Override
    public void deliver(Student student, Notice notice) {
        System.out.printf("[SMS → %s] %s.%n", student.getName(), notice.getTitle());
    }
}

class Student {
    private String name;
    private String department;
    private List<NotificationChannel> preferredChannels;

    public Student(String name, String department, List<NotificationChannel> preferredChannels) {
        this.name = name;
        this.department = department;
        this.preferredChannels = new ArrayList<>(preferredChannels);
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public List<NotificationChannel> getPreferredChannels() {
        return Collections.unmodifiableList(preferredChannels);
    }
}

class Notice {
    private String title;
    private List<String> targetDepartments;

    public Notice(String title, List<String> targetDepartments) {
        this.title = title;
        this.targetDepartments = targetDepartments == null ? new ArrayList<>() : new ArrayList<>(targetDepartments);
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTargetDepartments() {
        return Collections.unmodifiableList(targetDepartments);
    }
}

class NoticeBoard {
    private List<Student> registeredStudents;

    public NoticeBoard() {
        this.registeredStudents = new ArrayList<>();
    }

    public void registerStudent(Student student) {
        registeredStudents.add(student);
    }

    public boolean postNotice(Notice notice) {
        if (notice == null || notice.getTitle() == null || notice.getTitle().trim().isEmpty()) {
            System.out.println("Cannot post notice: Title is required.");
            return false;
        }

        if (notice.getTargetDepartments().isEmpty()) {
            System.out.println("Cannot post notice: At least one target department is required.");
            return false;
        }

        String depts = String.join(", ", notice.getTargetDepartments());
        System.out.printf("Notice '%s' posted to %s.%n", notice.getTitle(), depts);

        Set<String> targetSet = new HashSet<>(notice.getTargetDepartments());
        for (Student student : registeredStudents) {
            if (targetSet.contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getPreferredChannels()) {
                    channel.deliver(student, notice);
                }
            }
        }
        return true;
    }
}

public class CampusNoticeBroadcaster {
    public static void main(String[] args) {
        System.out.println("=== Problem 5: The Campus Notice Broadcaster ===");

        NoticeBoard noticeBoard = new NoticeBoard();

        // Asha (CSE) prefers Email and App
        Student asha = new Student("Asha", "CSE", List.of(new EmailChannel(), new AppChannel()));
        // Ravi (ECE) prefers SMS
        Student ravi = new Student("Ravi", "ECE", List.of(new SmsChannel()));

        noticeBoard.registerStudent(asha);
        noticeBoard.registerStudent(ravi);

        // Admin posts notice 'Lab Closed Tomorrow' for CSE
        Notice notice1 = new Notice("Lab Closed Tomorrow", List.of("CSE"));
        noticeBoard.postNotice(notice1);

        // Admin posts notice 'Fee Deadline Extended' for CSE and ECE
        Notice notice2 = new Notice("Fee Deadline Extended", List.of("CSE", "ECE"));
        noticeBoard.postNotice(notice2);

        // Admin attempts to post notice 'Sports Day' with no target department
        Notice notice3 = new Notice("Sports Day", List.of());
        noticeBoard.postNotice(notice3);

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter notice title: ");
                String title = scanner.nextLine().trim();
                System.out.print("Enter target department (CSE/ECE): ");
                String dept = scanner.nextLine().trim();
                noticeBoard.postNotice(new Notice(title, List.of(dept)));
            }
        }
    }
}
