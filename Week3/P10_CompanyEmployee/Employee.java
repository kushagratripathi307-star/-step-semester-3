package Week3.P10_CompanyEmployee;

public class Employee {
    String empName;
    double salary;

    static String companyName = "Bright Horizon Technologies";
    static int employeeCount = 0;

    public Employee(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }

    public static void printCompanyInfo() {
        System.out.println(companyName + " Employees on record: " + employeeCount);
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("Divya", 65000);
        Employee e2 = new Employee("Arjun", 50000);
        Employee e3 = new Employee("Rohan", 55000);

        Employee.printCompanyInfo();
    }
}
