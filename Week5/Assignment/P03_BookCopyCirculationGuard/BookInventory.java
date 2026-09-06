package Week5.Assignment.P03_BookCopyCirculationGuard;

public class BookInventory {
    private int copiesTotal;
    private int copiesAvailable;

    public BookInventory(int copiesTotal) {
        if (copiesTotal <= 0) {
            throw new IllegalArgumentException("copiesTotal must be a positive integer.");
        }
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }

    public void checkOut() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
        }
    }

    public void checkIn() {
        if (copiesAvailable < copiesTotal) {
            copiesAvailable++;
        }
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }

    public static void main(String[] args) {
        BookInventory b = new BookInventory(3);

        // 4 checkOut attempts (4th should be silently rejected)
        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();
        System.out.println("Available copies after 4 checkouts (capacity 3): " + b.getCopiesAvailable());

        // 4 checkIn attempts (4th should be silently rejected)
        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();
        System.out.println("Available copies after 4 checkins (capacity 3): " + b.getCopiesAvailable());
    }
}
