import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Week 10 - Category B - Practice Problem 5
 * Problem: Payroll Register System
 *
 * Task:
 * Develop a payroll system for different staff types: FullTime, PartTime, and Intern.
 * - FullTime: A fixed monthly salary.
 * - PartTime: Hours worked multiplied by an hourly rate.
 * - Intern: A fixed stipend.
 *
 * Store all staff members' payslips in an array/collection.
 * Output Format:
 * - Each staff member: "Payslip[name=<Name>, type=<Type>, pay=<CalculatedPay>]"
 * - "Total <SumOfAllPay>"
 * - "top earner <NameOfTopper>"
 */
public class PayrollRegisterSystem {

    // Payslip Data Transfer Object
    public static class Payslip {
        private final String name;
        private final String type;
        private final int pay;

        public Payslip(String name, String type, int pay) {
            this.name = name;
            this.type = type;
            this.pay = pay;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }

        public int getPay() {
            return pay;
        }

        @Override
        public String toString() {
            return "Payslip[name=" + name + ", type=" + type + ", pay=" + pay + "]";
        }
    }

    // Abstract Staff Base Class
    public static abstract class Staff {
        protected String name;

        public Staff(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public abstract String getType();

        public abstract int calculatePay();

        public Payslip generatePayslip() {
            return new Payslip(name, getType(), calculatePay());
        }
    }

    // Concrete Staff Subclasses
    public static class FullTimeStaff extends Staff {
        private final int salary;

        public FullTimeStaff(String name, int salary) {
            super(name);
            this.salary = salary;
        }

        @Override
        public String getType() {
            return "FullTime";
        }

        @Override
        public int calculatePay() {
            return salary;
        }
    }

    public static class PartTimeStaff extends Staff {
        private final int hours;
        private final int rate;

        public PartTimeStaff(String name, int hours, int rate) {
            super(name);
            this.hours = hours;
            this.rate = rate;
        }

        @Override
        public String getType() {
            return "PartTime";
        }

        @Override
        public int calculatePay() {
            return hours * rate;
        }
    }

    public static class InternStaff extends Staff {
        private final int stipend;

        public InternStaff(String name, int stipend) {
            super(name);
            this.stipend = stipend;
        }

        @Override
        public String getType() {
            return "Intern";
        }

        @Override
        public int calculatePay() {
            return stipend;
        }
    }

    // Payroll Registry Processor
    public static void processPayroll(List<Staff> staffMembers) {
        if (staffMembers == null || staffMembers.isEmpty()) {
            System.out.println("No staff members to process.");
            return;
        }

        // Store payslips in an array as required by the task
        Payslip[] payslips = new Payslip[staffMembers.size()];
        int totalPay = 0;
        int maxPay = -1;
        String topEarner = "";

        for (int i = 0; i < staffMembers.size(); i++) {
            payslips[i] = staffMembers.get(i).generatePayslip();
            totalPay += payslips[i].getPay();

            if (payslips[i].getPay() > maxPay) {
                maxPay = payslips[i].getPay();
                topEarner = payslips[i].getName();
            }
        }

        // Print each payslip
        for (Payslip p : payslips) {
            System.out.println(p);
        }

        // Print total and top earner
        System.out.println("Total " + totalPay);
        System.out.println("top earner " + topEarner);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Staff> staffList = new ArrayList<>();

        if (scanner.hasNextLine()) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() || line.equalsIgnoreCase("EXIT")) {
                    break;
                }
                String[] parts = line.split("\\s+");
                String type = parts[0];
                if (type.equalsIgnoreCase("FullTime") && parts.length >= 3) {
                    staffList.add(new FullTimeStaff(parts[1], Integer.parseInt(parts[2])));
                } else if (type.equalsIgnoreCase("PartTime") && parts.length >= 4) {
                    staffList.add(new PartTimeStaff(parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
                } else if (type.equalsIgnoreCase("Intern") && parts.length >= 3) {
                    staffList.add(new InternStaff(parts[1], Integer.parseInt(parts[2])));
                }
            }
            if (!staffList.isEmpty()) {
                processPayroll(staffList);
            }
        } else {
            // Default sample execution from the PDF
            System.out.println("=== Payroll Register System Demo ===");
            staffList.add(new FullTimeStaff("Asha", 50000));
            staffList.add(new PartTimeStaff("Ravi", 80, 300));
            staffList.add(new InternStaff("Neha", 15000));
            processPayroll(staffList);
        }
    }
}
