package Week4.P01_LibraryBookCataloguing;

public class LibraryBook {
    String title;
    String isbn;
    boolean catalogued;

    public LibraryBook(String title, String isbn) {
        this.title = title;
        if (isbn == null || isbn.trim().isEmpty()) {
            this.isbn = "PENDING";
        } else {
            this.isbn = isbn;
        }
        this.catalogued = true;
    }

    public LibraryBook(String title) {
        this(title, "PENDING");
    }

    public void printStatus() {
        System.out.printf("%s | %s | Catalogued: %b\n", title, isbn, catalogued);
    }

    public static void main(String[] args) {
        String[] titles = {"Clean Code", "Untitled Draft", "1984", "Notes"};
        String[] isbns = {"978-0132350884", "", "9780451524935", ""};

        for (int i = 0; i < titles.length; i++) {
            LibraryBook book;
            if (isbns[i].isEmpty()) {
                book = new LibraryBook(titles[i]);
            } else {
                book = new LibraryBook(titles[i], isbns[i]);
            }
            book.printStatus();
        }
    }
}
