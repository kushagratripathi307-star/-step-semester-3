import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Week 10 - Category B - Practice Problem 4
 * Problem: Class Marks Grid Analysis
 *
 * Task:
 * Manage and analyze student marks across 3 subjects stored in a 2D array (grid).
 * Calculate:
 * 1. Total marks for each student.
 * 2. Average marks for each subject (formatted to 2 decimal places).
 * 3. Identify the topper (highest total marks).
 *
 * Input Format:
 * <StudentName> [<Subject1Marks>, <Subject2Marks>, <Subject3Marks>]
 *
 * Expected Output:
 * Totals <Name1> <Total1>, <Name2> <Total2>, ...
 * averages <Avg1>, <Avg2>, <Avg3>
 * topper <TopperName> (<TopperTotal>)
 */
public class ClassMarksGridAnalysis {

    public static class StudentRecord {
        String name;
        int[] marks;
        int total;

        public StudentRecord(String name, int[] marks) {
            this.name = name;
            this.marks = marks;
            this.total = 0;
            for (int m : marks) {
                this.total += m;
            }
        }
    }

    /**
     * Parses a line such as: "Asha [78, 85, 90]"
     */
    public static StudentRecord parseRecord(String line) {
        if (line == null || line.trim().isEmpty()) {
            return null;
        }
        line = line.trim();
        int bracketStart = line.indexOf('[');
        int bracketEnd = line.indexOf(']');

        if (bracketStart == -1 || bracketEnd == -1 || bracketEnd <= bracketStart) {
            return null;
        }

        String name = line.substring(0, bracketStart).trim();
        String marksStr = line.substring(bracketStart + 1, bracketEnd);
        String[] tokens = marksStr.split("[,\\s]+");
        List<Integer> list = new ArrayList<>();
        for (String t : tokens) {
            if (!t.isEmpty()) {
                list.add(Integer.parseInt(t));
            }
        }

        int[] marks = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            marks[i] = list.get(i);
        }

        return new StudentRecord(name, marks);
    }

    /**
     * Analyzes records using a 2D array and prints the formatted results.
     */
    public static void analyzeGrid(List<StudentRecord> students) {
        if (students == null || students.isEmpty()) {
            System.out.println("No student records to analyze.");
            return;
        }

        int numStudents = students.size();
        int numSubjects = 3;

        // 2D grid of marks: grid[i][j] where i is student index, j is subject index
        int[][] grid = new int[numStudents][numSubjects];
        String[] names = new String[numStudents];
        int[] studentTotals = new int[numStudents];

        int topperIndex = 0;
        int highestTotal = -1;

        for (int i = 0; i < numStudents; i++) {
            StudentRecord s = students.get(i);
            names[i] = s.name;
            int total = 0;
            for (int j = 0; j < numSubjects && j < s.marks.length; j++) {
                grid[i][j] = s.marks[j];
                total += s.marks[j];
            }
            studentTotals[i] = total;
            if (total > highestTotal) {
                highestTotal = total;
                topperIndex = i;
            }
        }

        // 1. Print student totals: "Totals Asha 253, Ravi 259, Neha 230"
        StringBuilder totalsSb = new StringBuilder("Totals ");
        for (int i = 0; i < numStudents; i++) {
            totalsSb.append(names[i]).append(" ").append(studentTotals[i]);
            if (i < numStudents - 1) {
                totalsSb.append(", ");
            }
        }
        System.out.println(totalsSb);

        // 2. Calculate subject averages across columns
        double[] subjectAverages = new double[numSubjects];
        StringBuilder avgSb = new StringBuilder("averages ");
        for (int j = 0; j < numSubjects; j++) {
            int colSum = 0;
            for (int i = 0; i < numStudents; i++) {
                colSum += grid[i][j];
            }
            subjectAverages[j] = (double) colSum / numStudents;
            avgSb.append(String.format(Locale.US, "%.2f", subjectAverages[j]));
            if (j < numSubjects - 1) {
                avgSb.append(", ");
            }
        }
        System.out.println(avgSb);

        // 3. Print topper: "topper Ravi (259)"
        System.out.println("topper " + names[topperIndex] + " (" + highestTotal + ")");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<StudentRecord> students = new ArrayList<>();

        if (scanner.hasNextLine()) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() || line.equalsIgnoreCase("EXIT")) {
                    break;
                }
                StudentRecord record = parseRecord(line);
                if (record != null) {
                    students.add(record);
                }
            }
            if (!students.isEmpty()) {
                analyzeGrid(students);
            }
        } else {
            // Default sample from the PDF
            System.out.println("=== Class Marks Grid Analysis Demo ===");
            students.add(parseRecord("Asha [78, 85, 90]"));
            students.add(parseRecord("Ravi [88, 92, 79]"));
            students.add(parseRecord("Neha [65, 70, 95]"));
            analyzeGrid(students);
        }
    }
}
