package Week8.Practice.P04_HotelBookingSystem;

import java.util.*;

abstract class Room {
    private String roomNumber;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public abstract String getCategory();
    public abstract double getRatePerNight();

    public double calculatePrice(int nights) {
        return nights * getRatePerNight();
    }

    public String getFullName() {
        return getCategory() + " " + roomNumber;
    }
}

class StandardRoom extends Room {
    public StandardRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public String getCategory() {
        return "Standard Room";
    }

    @Override
    public double getRatePerNight() {
        return 100.00;
    }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public String getCategory() {
        return "Deluxe Room";
    }

    @Override
    public double getRatePerNight() {
        return 180.00;
    }
}

class SuiteRoom extends Room {
    public SuiteRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public String getCategory() {
        return "Suite";
    }

    @Override
    public double getRatePerNight() {
        return 300.00;
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

class Reservation {
    private Customer customer;
    private Room room;
    private String dateRangeString;
    private int startDay;
    private int endDay;
    private double price;
    private boolean cancelled;

    public Reservation(Customer customer, Room room, String dateRangeString, int startDay, int endDay) {
        this.customer = customer;
        this.room = room;
        this.dateRangeString = dateRangeString;
        this.startDay = startDay;
        this.endDay = endDay;
        int nights = Math.max(1, endDay - startDay);
        this.price = room.calculatePrice(nights);
        this.cancelled = false;
    }

    public boolean overlapsWith(int otherStart, int otherEnd) {
        if (cancelled) return false;
        return (startDay < otherEnd && otherStart < endDay);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public String getDateRangeString() {
        return dateRangeString;
    }

    public double getPrice() {
        return price;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}

class Hotel {
    private List<Reservation> reservations;

    public Hotel() {
        this.reservations = new ArrayList<>();
    }

    public boolean isAvailable(Room room, int startDay, int endDay) {
        for (Reservation r : reservations) {
            if (r.getRoom().getRoomNumber().equals(room.getRoomNumber()) && r.overlapsWith(startDay, endDay)) {
                return false;
            }
        }
        return true;
    }

    public void checkAvailability(Room room, String dateRangeString, int startDay, int endDay) {
        if (isAvailable(room, startDay, endDay)) {
            System.out.printf("%s is available from %s.%n", room.getFullName(), dateRangeString);
        } else {
            System.out.printf("%s is not available from %s.%n", room.getFullName(), dateRangeString);
        }
    }

    public Reservation reserve(Customer customer, Room room, String dateRangeString, int startDay, int endDay) {
        if (!isAvailable(room, startDay, endDay)) {
            System.out.printf("%s is not available from %s.%n", room.getFullName(), dateRangeString);
            return null;
        }

        Reservation res = new Reservation(customer, room, dateRangeString, startDay, endDay);
        reservations.add(res);
        System.out.printf("Reservation confirmed for %s, %s (%s). Price: $%.2f.%n",
                customer.getName(), room.getFullName(), dateRangeString, res.getPrice());
        return res;
    }

    public boolean cancelReservation(Reservation reservation) {
        if (reservation == null || reservation.isCancelled()) {
            System.out.println("Invalid or already cancelled reservation.");
            return false;
        }

        reservation.setCancelled(true);
        System.out.printf("Reservation for %s, %s (%s) cancelled successfully.%n",
                reservation.getCustomer().getName(), reservation.getRoom().getFullName(), reservation.getDateRangeString());
        return true;
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        System.out.println("=== Problem 4: Hotel Booking System ===");

        Hotel hotel = new Hotel();

        Room room101 = new StandardRoom("101");
        Room room201 = new DeluxeRoom("201");

        Customer custA = new Customer("Customer A");
        Customer custB = new Customer("Customer B");
        Customer custC = new Customer("Customer C");

        // Customer A checks availability for Standard Room 101 from Jan 1 to Jan 5
        hotel.checkAvailability(room101, "Jan 1 to Jan 5", 1, 5);

        // Customer A reserves Standard Room 101 from Jan 1 to Jan 5
        Reservation resA = hotel.reserve(custA, room101, "Jan 1-5", 1, 5);

        // Customer B attempts to reserve Standard Room 101 from Jan 3 to Jan 7
        hotel.reserve(custB, room101, "Jan 3 to Jan 7", 3, 7);

        // Customer A cancels reservation for Standard Room 101 for Jan 1-5 (before deadline)
        hotel.cancelReservation(resA);

        // Customer C reserves Deluxe Room 201 from Feb 10 to Feb 12
        hotel.reserve(custC, room201, "Feb 10-12", 10, 12);

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter customer name: ");
                String name = scanner.nextLine().trim();
                hotel.reserve(new Customer(name), room201, "Feb 15-18", 15, 18);
            }
        }
    }
}
