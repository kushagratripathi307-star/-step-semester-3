package Week2.Assignment.P08_ProductInventoryParser;

public class ProductInventoryParser {

    public static void parseInventoryRecord(String csvLine) {
        if (csvLine == null) {
            System.out.println("Invalid Record");
            return;
        }

        String[] fields = csvLine.split(",");
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }

        String productName = fields[0].trim();
        String sku = fields[1].trim();
        String qty = fields[2].trim();

        System.out.printf("Product: %s | SKU: %s | Qty: %s\n", productName, sku, qty);
    }

    public static void main(String[] args) {
        System.out.print("\"Wireless Mouse,WM-2201,150\" -> ");
        parseInventoryRecord("Wireless Mouse,WM-2201,150");

        System.out.print("\"Wireless Mouse,150\" -> ");
        parseInventoryRecord("Wireless Mouse,150");
    }
}
