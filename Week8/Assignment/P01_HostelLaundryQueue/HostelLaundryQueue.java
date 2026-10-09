package Week8.Assignment.P01_HostelLaundryQueue;

import java.util.Scanner;

interface WashType {
    String getName();
    int getDurationMinutes();
    double getCharge();
}

class QuickWash implements WashType {
    @Override
    public String getName() {
        return "Quick wash";
    }

    @Override
    public int getDurationMinutes() {
        return 30;
    }

    @Override
    public double getCharge() {
        return 20.00;
    }
}

class NormalWash implements WashType {
    @Override
    public String getName() {
        return "Normal wash";
    }

    @Override
    public int getDurationMinutes() {
        return 45;
    }

    @Override
    public double getCharge() {
        return 30.00;
    }
}

class HeavyWash implements WashType {
    @Override
    public String getName() {
        return "Heavy wash";
    }

    @Override
    public int getDurationMinutes() {
        return 60;
    }

    @Override
    public double getCharge() {
        return 45.00;
    }
}

class DelicateWash implements WashType {
    @Override
    public String getName() {
        return "Delicate wash";
    }

    @Override
    public int getDurationMinutes() {
        return 35;
    }

    @Override
    public double getCharge() {
        return 25.00;
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

class WashingMachine {
    private String machineId;
    private boolean busy;

    public WashingMachine(String machineId) {
        this.machineId = machineId;
        this.busy = false;
    }

    public String getMachineId() {
        return machineId;
    }

    public boolean isBusy() {
        return busy;
    }

    public WashCycle startWash(Student student, WashType washType) {
        if (busy) {
            System.out.println("Machine " + machineId + " is currently busy.");
            return null;
        }
        this.busy = true;
        WashCycle cycle = new WashCycle(student, this, washType);
        System.out.printf("%s started on %s for %s (%d min). Charge: ₹%.2f.%n",
                washType.getName(), machineId, student.getName(), washType.getDurationMinutes(), washType.getCharge());
        return cycle;
    }

    public void completeCycle() {
        if (busy) {
            busy = false;
            System.out.println(machineId + " cycle completed. " + machineId + " is now free.");
        } else {
            System.out.println(machineId + " is already free.");
        }
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public Student getStudent() {
        return student;
    }

    public WashingMachine getMachine() {
        return machine;
    }

    public WashType getWashType() {
        return washType;
    }
}

public class HostelLaundryQueue {
    public static void main(String[] args) {
        System.out.println("=== Problem 1: The Hostel Laundry Queue ===");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        // Asha starts a Quick wash on Machine M1
        m1.startWash(asha, new QuickWash());

        // Ravi attempts to start a Heavy wash on Machine M1
        m1.startWash(ravi, new HeavyWash());

        // Ravi starts a Heavy wash on Machine M2
        m2.startWash(ravi, new HeavyWash());

        // Machine M1 completes its cycle
        m1.completeCycle();

        // Neha starts a Normal wash on Machine M1
        m1.startWash(neha, new NormalWash());

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter student name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Select Wash Type (1: Quick, 2: Normal, 3: Heavy, 4: Delicate): ");
                int choice = 1;
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                }
                WashType type = switch (choice) {
                    case 2 -> new NormalWash();
                    case 3 -> new HeavyWash();
                    case 4 -> new DelicateWash();
                    default -> new QuickWash();
                };
                m2.completeCycle();
                m2.startWash(new Student(name), type);
            }
        }
    }
}
