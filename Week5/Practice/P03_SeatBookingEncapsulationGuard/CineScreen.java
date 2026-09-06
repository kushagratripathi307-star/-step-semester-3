package Week5.Practice.P03_SeatBookingEncapsulationGuard;

public class CineScreen {
    private int seatsTotal;
    private int seatsAvailable;

    public CineScreen(int seatsTotal) {
        if (seatsTotal <= 0) {
            throw new IllegalArgumentException("Total seats must be greater than zero.");
        }
        this.seatsTotal = seatsTotal;
        this.seatsAvailable = seatsTotal;
    }

    public void bookSeat() {
        if (seatsAvailable > 0) {
            seatsAvailable--;
        }
    }

    public void cancelBooking() {
        if (seatsAvailable < seatsTotal) {
            seatsAvailable++;
        }
    }

    public int getSeatsAvailable() {
        return seatsAvailable;
    }

    public static void main(String[] args) {
        // Example 1: Construct with 0 seats
        try {
            new CineScreen(0);
        } catch (IllegalArgumentException e) {
            System.out.println("construction rejected");
        }

        // Example 2: Booking seats beyond available capacity
        CineScreen c = new CineScreen(2);
        c.bookSeat();
        c.bookSeat();
        c.bookSeat(); // 3rd booking attempt (rejected)
        System.out.println("Seats available after 3 bookings: " + c.getSeatsAvailable());

        // Example 3: Cancelling bookings beyond total capacity
        c.cancelBooking();
        c.cancelBooking();
        c.cancelBooking(); // 3rd cancellation attempt (rejected)
        System.out.println("Seats available after 3 cancellations: " + c.getSeatsAvailable());
    }
}
