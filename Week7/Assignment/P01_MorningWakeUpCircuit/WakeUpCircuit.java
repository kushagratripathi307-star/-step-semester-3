package Week7.Assignment.P01_MorningWakeUpCircuit;

import java.util.Scanner;

interface Ringable {
    String ring();
}

class AlarmClock implements Ringable {
    private String time;

    public AlarmClock(String time) {
        this.time = time;
    }

    @Override
    public String ring() {
        return "Alarm ringing for " + time;
    }

    public String getTime() {
        return time;
    }
}

class Doorbell implements Ringable {
    private String location;

    public Doorbell(String location) {
        this.location = location;
    }

    @Override
    public String ring() {
        return "Doorbell ringing at " + location;
    }

    public String getLocation() {
        return location;
    }
}

public class WakeUpCircuit {

    public static void ringAll(Ringable[] devices) {
        if (devices != null) {
            for (Ringable device : devices) {
                if (device != null) {
                    System.out.println(device.ring());
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 1: Morning Wake-Up Circuit ===");

        AlarmClock clock = new AlarmClock("7:00 AM");
        Doorbell doorbell = new Doorbell("Front Door");

        System.out.println("Alarm clock output: " + clock.ring());
        System.out.println("Doorbell output: " + doorbell.ring());

        System.out.println("\n--- ringAll Output ---");
        Ringable[] devices = {clock, doorbell};
        ringAll(devices);

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter Alarm Time: ");
                String t = scanner.nextLine();
                AlarmClock ac = new AlarmClock(t);
                System.out.println("Result: " + ac.ring());
            }
        }
    }
}
