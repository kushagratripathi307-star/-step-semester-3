package Week8.Practice.P02_EmployeeLeaveRequestWorkflow;

import java.util.Scanner;

enum LeaveStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected");

    private final String displayName;

    LeaveStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

abstract class Employee {
    private String name;

    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String getRole();

    public LeaveRequest submitLeave(String dateRange) {
        LeaveRequest req = new LeaveRequest(this, dateRange);
        System.out.printf("Leave request submitted for %s (%s). Status: %s.%n",
                name, dateRange, req.getStatus().getDisplayName());
        return req;
    }
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) {
        super(name);
    }

    @Override
    public String getRole() {
        return "Full Time";
    }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) {
        super(name);
    }

    @Override
    public String getRole() {
        return "Part Time";
    }
}

class Contractor extends Employee {
    public Contractor(String name) {
        super(name);
    }

    @Override
    public String getRole() {
        return "Contractor";
    }
}

class Manager {
    private String name;

    public Manager(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void approve(LeaveRequest request) {
        request.approveBy(this);
    }

    public void reject(LeaveRequest request) {
        request.rejectBy(this);
    }
}

class LeaveRequest {
    private Employee employee;
    private String dateRange;
    private LeaveStatus status;

    public LeaveRequest(Employee employee, String dateRange) {
        this.employee = employee;
        this.dateRange = dateRange;
        this.status = LeaveStatus.PENDING;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getDateRange() {
        return dateRange;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void approveBy(Manager manager) {
        if (status != LeaveStatus.PENDING) {
            System.out.printf("Cannot review: Request is already %s.%n", status.getDisplayName());
            return;
        }
        this.status = LeaveStatus.APPROVED;
        System.out.printf("%s's leave request (%s) approved. Status: %s.%n",
                employee.getName(), dateRange, status.getDisplayName());
    }

    public void rejectBy(Manager manager) {
        if (status != LeaveStatus.PENDING) {
            System.out.printf("Cannot review: Request is already %s.%n", status.getDisplayName());
            return;
        }
        this.status = LeaveStatus.REJECTED;
        System.out.printf("%s's leave request (%s) rejected. Status: %s.%n",
                employee.getName(), dateRange, status.getDisplayName());
    }

    public boolean setPending() {
        if (status == LeaveStatus.APPROVED || status == LeaveStatus.REJECTED) {
            System.out.printf("Cannot change leave request status from %s to Pending.%n", status.getDisplayName());
            return false;
        }
        this.status = LeaveStatus.PENDING;
        return true;
    }
}

public class EmployeeLeaveRequestWorkflow {
    public static void main(String[] args) {
        System.out.println("=== Problem 2: Employee Leave Request Workflow ===");

        Employee john = new FullTimeEmployee("John");
        Employee jane = new PartTimeEmployee("Jane");

        Manager alice = new Manager("Alice");
        Manager bob = new Manager("Bob");

        // FullTimeEmployee John submits leave request for 5 days (Jan 1-5)
        LeaveRequest johnReq = john.submitLeave("Jan 1-5");

        // Manager Alice reviews John's request and approves it
        alice.approve(johnReq);

        // PartTimeEmployee Jane submits leave request for 2 days (Feb 10-11)
        LeaveRequest janeReq = jane.submitLeave("Feb 10-11");

        // Manager Bob reviews Jane's request and rejects it
        bob.reject(janeReq);

        // John attempts to change his approved leave request to Pending
        johnReq.setPending();

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter employee name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Enter leave dates (e.g. Mar 1-3): ");
                String dates = scanner.nextLine().trim();
                LeaveRequest req = new FullTimeEmployee(name).submitLeave(dates);
                alice.approve(req);
            }
        }
    }
}
