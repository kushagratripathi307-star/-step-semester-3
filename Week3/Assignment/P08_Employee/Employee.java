package Week3.Assignment.P08_Employee;

public class Employee {
    String empId;
    String empName;
    double salary;
    boolean isIntern;

    public Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
        this.isIntern = false;
    }

    public Employee(String empId, String empName) {
        this(empId, empName, 0.0);
        this.isIntern = true;
    }

    public void printProfile() {
        System.out.printf("%s | %s | Rs %.1f | Intern: %b\n", empId, empName, salary, isIntern);
    }

    public static void main(String[] args) {
        Employee permEmp = new Employee("E-101", "Divya", 65000.0);
        Employee internEmp = new Employee("E-102", "Arjun");

        permEmp.printProfile();
        internEmp.printProfile();
    }
}
