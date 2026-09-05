package Week4.P04_OneTimeCollegeSetup;

public class SrmStudent {
    static String collegeName;
    static String academicYear;

    String name;

    static {
        collegeName = "SRM Institute of Science and Technology";
        academicYear = "2026-2027";
        System.out.println("College info loaded");
    }

    public SrmStudent(String name) {
        this.name = name;
        System.out.println("Student record created: " + this.name);
    }

    public static void main(String[] args) {
        String[] names = {"Ravi", "Meera", "Karthik", "Divya", "Anitha"};
        for (String studentName : names) {
            new SrmStudent(studentName);
        }
    }
}
