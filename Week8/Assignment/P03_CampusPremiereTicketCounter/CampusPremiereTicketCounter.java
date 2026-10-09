package Week8.Assignment.P03_CampusPremiereTicketCounter;

import java.util.*;

abstract class Seat {
    private String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public abstract double getPrice();
    public abstract String getCategory();
}

class RegularSeat extends Seat {
    public RegularSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 150.00;
    }

    @Override
    public String getCategory() {
        return "Regular";
    }
}

class PremiumSeat extends Seat {
    public PremiumSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 250.00;
    }

    @Override
    public String getCategory() {
        return "Premium";
    }
}

class ReclinerSeat extends Seat {
    public ReclinerSeat(String seatNumber) {
        super(seatNumber);
    }

    @Override
    public double getPrice() {
        return 400.00;
    }

    @Override
    public String getCategory() {
        return "Recliner";
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

class Booking {
    private Customer customer;
    private Show show;
    private List<Seat> seats;
    private double totalAmount;
    private boolean cancelled;

    public Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>(seats);
        this.cancelled = false;
        this.totalAmount = 0;
        for (Seat s : seats) {
            this.totalAmount += s.getPrice();
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public Show getShow() {
        return show;
    }

    public List<Seat> getSeats() {
        return Collections.unmodifiableList(seats);
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getSeatNumbersString() {
        List<String> list = new ArrayList<>();
        for (Seat s : seats) {
            list.add(s.getSeatNumber());
        }
        return String.join(", ", list);
    }
}

class Show {
    private String showTime;
    private boolean showStarted;
    private Set<String> bookedSeatNumbers;

    public Show(String showTime) {
        this.showTime = showTime;
        this.showStarted = false;
        this.bookedSeatNumbers = new HashSet<>();
    }

    public String getShowTime() {
        return showTime;
    }

    public boolean isShowStarted() {
        return showStarted;
    }

    public void setShowStarted(boolean showStarted) {
        this.showStarted = showStarted;
    }

    public Booking bookSeats(Customer customer, List<Seat> requestedSeats) {
        if (showStarted) {
            System.out.println("Cannot book seats: Show has already started.");
            return null;
        }

        if (requestedSeats == null || requestedSeats.isEmpty()) {
            System.out.println("Cannot book: No seats selected.");
            return null;
        }

        if (requestedSeats.size() > 6) {
            System.out.println("Cannot book more than 6 seats in a single booking.");
            return null;
        }

        for (Seat s : requestedSeats) {
            if (bookedSeatNumbers.contains(s.getSeatNumber())) {
                System.out.printf("Seat %s is already booked for this show.%n", s.getSeatNumber());
                return null;
            }
        }

        for (Seat s : requestedSeats) {
            bookedSeatNumbers.add(s.getSeatNumber());
        }

        Booking booking = new Booking(customer, this, requestedSeats);
        System.out.printf("Booking confirmed for %s: %s. Total: ₹%.2f.%n",
                customer.getName(), booking.getSeatNumbersString(), booking.getTotalAmount());
        return booking;
    }

    public boolean cancelBooking(Booking booking) {
        if (booking == null || booking.isCancelled()) {
            System.out.println("Invalid booking or already cancelled.");
            return false;
        }

        if (showStarted) {
            System.out.println("Cannot cancel booking after the show has started.");
            return false;
        }

        for (Seat s : booking.getSeats()) {
            bookedSeatNumbers.remove(s.getSeatNumber());
        }
        booking.setCancelled(true);
        System.out.printf("%s's booking cancelled. Seats %s released.%n",
                booking.getCustomer().getName(), booking.getSeatNumbersString());
        return true;
    }
}

public class CampusPremiereTicketCounter {
    public static void main(String[] args) {
        System.out.println("=== Problem 3: The Campus Premiere Ticket Counter ===");

        Show show7PM = new Show("7 PM");

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        // Asha books Regular seats A1 and A2 and Premium seat F5 for the 7 PM show
        List<Seat> ashaSeats = List.of(
                new RegularSeat("A1"),
                new RegularSeat("A2"),
                new PremiumSeat("F5")
        );
        Booking ashaBooking = show7PM.bookSeats(asha, ashaSeats);

        // Ravi attempts to book seat A2 for the same show
        show7PM.bookSeats(ravi, List.of(new RegularSeat("A2")));

        // Ravi books Recliner seat R1 for the same show
        Booking raviBooking = show7PM.bookSeats(ravi, List.of(new ReclinerSeat("R1")));

        // Asha cancels her booking before the show starts
        show7PM.cancelBooking(ashaBooking);

        // Neha books seat A2 for the same show
        Booking nehaBooking = show7PM.bookSeats(neha, List.of(new RegularSeat("A2")));

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter customer name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Enter seat number (e.g. B1): ");
                String seatNo = scanner.nextLine().trim();
                show7PM.bookSeats(new Customer(name), List.of(new RegularSeat(seatNo)));
            }
        }
    }
}
