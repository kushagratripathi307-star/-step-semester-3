package Week4.Practice.P02_PayrollBatchBonus;

public class Employee {
    String empId;
    double salary;

    public Employee(String empId, double salary) {
        this.empId = empId;
        this.salary = salary;
    }

    public void raiseSalary(double salary) {
        this.salary += salary;
    }

    public void printFinalSalary() {
        System.out.printf("%s | Final Salary: Rs %.1f\n", empId, salary);
    }

    public static void main(String[] args) {
        String[] empIds = {"E-101", "E-102", "E-103", "E-104"};
        double[] startingSalaries = {40000, 55000, 62000, 48000};
        double bonus = 5000;

        Employee[] employees = new Employee[empIds.length];
        for (int i = 0; i < empIds.length; i++) {
            employees[i] = new Employee(empIds[i], startingSalaries[i]);
            employees[i].raiseSalary(bonus);
            employees[i].printFinalSalary();
        }
    }
}
