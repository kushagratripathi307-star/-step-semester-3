import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Week 10 - Category B - Practice Problem 2
 * Problem: Campus Vehicle Pass System
 *
 * Task:
 * Manage campus vehicle passes and charging bay bookings.
 * Vehicle pass fees:
 * - Bike: 300
 * - Car: 1000
 * - E-Bike (EBike): 300
 * - E-Car (ECar): 1000
 * Only electric vehicles (EBike, ECar) are chargeable.
 *
 * Input Format:
 * - PASS <VehicleType> <PassNumber> <OwnerName>
 * - CHARGE <PassNumber>
 *
 * Output Format:
 * - For PASS:   "<PassNumber> (<VehicleType>) pass fee <Fee>"
 * - For CHARGE: "<PassNumber> charging bay allotted" OR
 *               "<PassNumber> rejected: charging unsupported"
 */
public class CampusVehiclePassSystem {

    // Interface for chargeable (electric) vehicles
    public interface Chargeable {
        boolean canCharge();
    }

    // Abstract Vehicle Base Class
    public static abstract class Vehicle {
        protected String passNumber;
        protected String ownerName;
        protected String vehicleType;

        public Vehicle(String passNumber, String ownerName, String vehicleType) {
            this.passNumber = passNumber;
            this.ownerName = ownerName;
            this.vehicleType = vehicleType;
        }

        public String getPassNumber() {
            return passNumber;
        }

        public String getOwnerName() {
            return ownerName;
        }

        public String getVehicleType() {
            return vehicleType;
        }

        public abstract int getPassFee();
    }

    // Concrete Vehicle Classes
    public static class Bike extends Vehicle {
        public Bike(String passNumber, String ownerName) {
            super(passNumber, ownerName, "Bike");
        }

        @Override
        public int getPassFee() {
            return 300;
        }
    }

    public static class Car extends Vehicle {
        public Car(String passNumber, String ownerName) {
            super(passNumber, ownerName, "Car");
        }

        @Override
        public int getPassFee() {
            return 1000;
        }
    }

    public static class EBike extends Vehicle implements Chargeable {
        public EBike(String passNumber, String ownerName, String displayType) {
            super(passNumber, ownerName, displayType);
        }

        @Override
        public int getPassFee() {
            return 300;
        }

        @Override
        public boolean canCharge() {
            return true;
        }
    }

    public static class ECar extends Vehicle implements Chargeable {
        public ECar(String passNumber, String ownerName, String displayType) {
            super(passNumber, ownerName, displayType);
        }

        @Override
        public int getPassFee() {
            return 1000;
        }

        @Override
        public boolean canCharge() {
            return true;
        }
    }

    // Campus Pass Manager
    public static class PassManager {
        private final Map<String, Vehicle> vehicleRegistry = new HashMap<>();

        public void processCommand(String line) {
            if (line == null || line.trim().isEmpty()) {
                return;
            }
            String[] parts = line.trim().split("\\s+");
            String command = parts[0];

            if (command.equalsIgnoreCase("PASS")) {
                if (parts.length >= 4) {
                    String typeStr = parts[1];
                    String passNum = parts[2];
                    String owner = parts[3];

                    Vehicle vehicle;
                    String normalized = typeStr.replace("-", "").toLowerCase();
                    if (normalized.equals("bike")) {
                        vehicle = new Bike(passNum, owner);
                    } else if (normalized.equals("car")) {
                        vehicle = new Car(passNum, owner);
                    } else if (normalized.equals("ebike")) {
                        vehicle = new EBike(passNum, owner, typeStr);
                    } else if (normalized.equals("ecar")) {
                        vehicle = new ECar(passNum, owner, typeStr);
                    } else {
                        System.out.println("Unknown vehicle type: " + typeStr);
                        return;
                    }

                    vehicleRegistry.put(passNum, vehicle);
                    System.out.println(passNum + " (" + typeStr + ") pass fee " + vehicle.getPassFee());
                }
            } else if (command.equalsIgnoreCase("CHARGE")) {
                if (parts.length >= 2) {
                    String passNum = parts[1];
                    Vehicle vehicle = vehicleRegistry.get(passNum);
                    if (vehicle == null) {
                        System.out.println(passNum + " rejected: vehicle not registered");
                    } else if (vehicle instanceof Chargeable) {
                        System.out.println(passNum + " charging bay allotted");
                    } else {
                        System.out.println(passNum + " rejected: charging unsupported");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            PassManager manager = new PassManager();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() || line.equalsIgnoreCase("EXIT")) {
                    break;
                }
                manager.processCommand(line);
            }
        } else {
            // Default sample execution from the PDF
            System.out.println("=== Campus Vehicle Pass System Demo ===");
            String[] sampleOperations = {
                    "PASS Bike KA01 Asha",
                    "PASS ECar KA02 Ravi",
                    "CHARGE KA02",
                    "CHARGE KA01"
            };

            PassManager manager = new PassManager();
            for (String op : sampleOperations) {
                manager.processCommand(op);
            }
        }
    }
}
