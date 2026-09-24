package Week7.Assignment.P05_SkylineDeliveryFleet;

import java.util.Scanner;

abstract class Drone {
    private String id;

    public Drone(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public abstract String fly();
}

interface Trackable {
    String getLocation();
}

class DeliveryDrone extends Drone implements Trackable {

    public DeliveryDrone(String id) {
        super(id);
    }

    @Override
    public String fly() {
        return "Delivery drone " + getId() + " flying";
    }

    @Override
    public String getLocation() {
        return getId() + " at Sector 4";
    }
}

class ScoutDrone extends Drone {

    public ScoutDrone(String id) {
        super(id);
    }

    @Override
    public String fly() {
        return "Scout drone " + getId() + " flying";
    }
}

class GroundRobot implements Trackable {
    private String id;

    public GroundRobot(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String getLocation() {
        return getId() + " at Sector 4";
    }
}

public class DeliveryFleet {

    public static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            Trackable t = (Trackable) o;
            return t.getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 5: Skyline Delivery Fleet ===");

        DeliveryDrone d = new DeliveryDrone("DR-1");
        ScoutDrone s = new ScoutDrone("SC-1");
        GroundRobot g = new GroundRobot("GR-1");

        System.out.println("getLocationIfTrackable(DeliveryDrone): " + getLocationIfTrackable(d));
        System.out.println("getLocationIfTrackable(ScoutDrone): " + getLocationIfTrackable(s));
        System.out.println("getLocationIfTrackable(GroundRobot): " + getLocationIfTrackable(g));

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter Drone ID: ");
                String id = scanner.nextLine();
                DeliveryDrone dd = new DeliveryDrone(id);
                System.out.println("Status: " + dd.fly() + " | Location: " + getLocationIfTrackable(dd));
            }
        }
    }
}
