package Week7.Assignment.P04_DigitalClassroomSetup;

import java.util.Scanner;

abstract class ClassroomDevice {
    private String assetTag;

    public ClassroomDevice(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public abstract String operate();
}

interface Chargeable {
    String charge();
    String charge(int minutes);
}

class Tablet extends ClassroomDevice implements Chargeable {

    public Tablet(String assetTag) {
        super(assetTag);
    }

    @Override
    public String operate() {
        return "Tablet " + getAssetTag() + " displaying lesson";
    }

    @Override
    public String charge() {
        return getAssetTag() + " charging";
    }

    @Override
    public String charge(int minutes) {
        return getAssetTag() + " charging for " + minutes + " minutes";
    }
}

public class ClassroomSetup {

    public static void main(String[] args) {
        System.out.println("=== Problem 4: Digital Classroom Setup ===");

        Tablet t = new Tablet("TAB-5");
        System.out.println("operate(): " + t.operate());
        System.out.println("charge(): " + t.charge());
        System.out.println("charge(30): " + t.charge(30));

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter Tablet Asset Tag: ");
                String tag = scanner.nextLine();
                System.out.print("Enter Charging Minutes: ");
                int mins = Integer.parseInt(scanner.nextLine());
                Tablet userTab = new Tablet(tag);
                System.out.println(userTab.operate());
                System.out.println(userTab.charge(mins));
            }
        }
    }
}
