package Week7.Practice.P02_WarehouseLabelPrinter;

import java.util.Scanner;

interface Printable {
    String printLabel();
}

class PackageBox implements Printable {
    private String trackingId;

    public PackageBox(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String printLabel() {
        return "Package label: " + trackingId;
    }

    public String getTrackingId() {
        return trackingId;
    }
}

class Invoice implements Printable {
    private String invoiceNumber;

    public Invoice(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String printLabel() {
        return "Invoice label: " + invoiceNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }
}

public class LabelPrinter {

    public static void printAll(Printable[] items) {
        if (items != null) {
            for (Printable item : items) {
                if (item != null) {
                    System.out.println(item.printLabel());
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 2: Warehouse Label Printer ===");

        PackageBox p = new PackageBox("TRK-88");
        Invoice i = new Invoice("INV-42");

        System.out.println("PackageBox printLabel(): " + p.printLabel());
        System.out.println("Invoice printLabel(): " + i.printLabel());

        System.out.println("\n--- printAll Output ---");
        Printable[] items = {p, i};
        printAll(items);

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter Tracking ID for PackageBox: ");
                String trk = scanner.nextLine();
                PackageBox userP = new PackageBox(trk);
                System.out.println(userP.printLabel());
            }
        }
    }
}
