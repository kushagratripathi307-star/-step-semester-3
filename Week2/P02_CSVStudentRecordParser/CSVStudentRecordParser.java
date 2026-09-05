package Week2.P02_CSVStudentRecordParser;

public class CSVStudentRecordParser {

    public static void parseStudentRecord(String csvLine) {
        if (csvLine == null) {
            System.out.println("Invalid Record");
            return;
        }

        String[] fields = csvLine.split(",");
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String name = fields[0].trim();
        String rollNo = fields[1].trim();
        String dept = fields[2].trim();

        System.out.printf("Name: %s | Roll No: %s | Dept: %s\n", name, rollNo, dept);
    }

    public static void main(String[] args) {
        System.out.print("\"Ananya Verma,RA2211003010123,CSE\" -> ");
        parseStudentRecord("Ananya Verma,RA2211003010123,CSE");

        System.out.print("\"Ananya Verma,CSE\" -> ");
        parseStudentRecord("Ananya Verma,CSE");
    }
}
