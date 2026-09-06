package Week5.Practice.P05_ImmutableBookingReceipt;

import java.util.Arrays;

public class BookingReceipt {
    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers) {
        this.bookingId = bookingId;
        this.seatNumbers = (seatNumbers == null) ? new String[0] : seatNumbers.clone();
    }

    public String getBookingId() {
        return bookingId;
    }

    public String[] getSeatNumbers() {
        return (seatNumbers == null) ? new String[0] : seatNumbers.clone();
    }

    public BookingReceipt withUpdatedSeat(int index, String newSeat) {
        String[] updatedSeats = getSeatNumbers();
        if (index >= 0 && index < updatedSeats.length) {
            updatedSeats[index] = newSeat;
        }
        return new BookingReceipt(this.bookingId, updatedSeats);
    }

    public static String processNightlySettlement(BookingReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        if (receipts != null) {
            for (BookingReceipt receipt : receipts) {
                if (receipt == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (receipt instanceof GroupBookingReceipt) {
                        group++;
                    } else {
                        individual++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + group + " group | " + individual + " individual";
    }

    public static void main(String[] args) {
        // Example 1: Defensive copying test
        BookingReceipt b = new BookingReceipt("CH-1001", new String[]{"A1", "A2"});
        String[] seats = b.getSeatNumbers();
        seats[0] = "X";
        System.out.println("Test 1 Defensive Copy: " + b.getSeatNumbers()[0]);

        // Example 2: Immutable updated seat (wither pattern)
        BookingReceipt updated = b.withUpdatedSeat(1, "A3");
        System.out.println("Test 2 Original Seats: " + Arrays.toString(b.getSeatNumbers()));
        System.out.println("Test 2 Updated Seats:  " + Arrays.toString(updated.getSeatNumbers()));

        // Example 3: Nightly settlement aggregation
        BookingReceipt[] batch = new BookingReceipt[]{
            new GroupBookingReceipt("CH-2002", new String[]{"B1", "B2"}, 2),
            null,
            new BookingReceipt("CH-3003", new String[]{"C1"})
        };
        System.out.println("Test 3 Settlement: " + processNightlySettlement(batch));
    }
}

class GroupBookingReceipt extends BookingReceipt {
    private final int groupSize;

    public GroupBookingReceipt(String bookingId, String[] seatNumbers, int groupSize) {
        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }

    public int getGroupSize() {
        return groupSize;
    }
}
