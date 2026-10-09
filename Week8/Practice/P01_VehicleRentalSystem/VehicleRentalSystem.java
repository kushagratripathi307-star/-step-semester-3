package Week8.Practice.P01_VehicleRentalSystem;

import java.util.Scanner;

abstract class Vehicle {
    private String name;
    private boolean available;

    public Vehicle(String name) {
        this.name = name;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public abstract String getCategory();
    public abstract double calculateRentalCharge(int days);
}

class Sedan extends Vehicle {
    public Sedan(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "Sedan";
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * 50.00; // $50/day
    }
}

class SUV extends Vehicle {
    public SUV(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "SUV";
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * 80.00; // $80/day
    }
}

class Truck extends Vehicle {
    public Truck(String name) {
        super(name);
    }

    @Override
    public String getCategory() {
        return "Truck";
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * 100.00; // $100/day
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Customer customer;
    private Vehicle vehicle;
    private int days;
    private double charge;

    public Rental(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.charge = vehicle.calculateRentalCharge(days);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getDays() {
        return days;
    }

    public double getCharge() {
        return charge;
    }
}

public class VehicleRentalSystem {

    public static Rental rentVehicle(Customer customer, Vehicle vehicle, int days) {
        if (!vehicle.isAvailable()) {
            System.out.printf("%s is currently unavailable.%n", vehicle.getName());
            return null;
        }

        vehicle.setAvailable(false);
        Rental rental = new Rental(customer, vehicle, days);
        System.out.printf("%s rented successfully by %s. Rental charge: $%.2f.%n",
                vehicle.getName(), customer.getName(), rental.getCharge());
        return rental;
    }

    public static void returnVehicle(Customer customer, Vehicle vehicle) {
        vehicle.setAvailable(true);
        System.out.printf("%s returned by %s.%n", vehicle.getName(), customer.getName());
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 1: Vehicle Rental System ===");

        Vehicle sedanA = new Sedan("Sedan A");
        Vehicle suvB = new SUV("SUV B");

        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        // Customer 1 rents Sedan A for 3 days
        Rental r1 = rentVehicle(c1, sedanA, 3);

        // Customer 2 attempts to rent Sedan A for 2 days
        Rental r2 = rentVehicle(c2, sedanA, 2);

        // Customer 1 returns Sedan A
        returnVehicle(c1, sedanA);

        // Customer 3 rents SUV B for 5 days
        Rental r3 = rentVehicle(c3, suvB, 5);

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter customer name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Enter rental days: ");
                int days = scanner.hasNextInt() ? scanner.nextInt() : 1;
                rentVehicle(new Customer(name), suvB, days);
            }
        }
    }
}
