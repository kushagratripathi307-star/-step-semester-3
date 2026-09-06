package Week5.Practice.P02_SubclassTicketAccess;

class MovieTicket {
    protected double ticketPrice;

    public MovieTicket(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}

class PremiumMovieTicket extends MovieTicket {
    private double discountRate;

    public PremiumMovieTicket(double ticketPrice, double discountRate) {
        super(ticketPrice);
        this.discountRate = discountRate;
    }

    public double getDiscountedPrice() {
        // Accessing protected field via own type (this.ticketPrice) is ALLOWED in subclass
        return this.ticketPrice * (1.0 - discountRate);
    }
}

public class AccessChecker {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";
            case "default":
                return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";
            case "protected":
                if ("SAME_CLASS".equals(accessorContext) ||
                    "SAME_PACKAGE".equals(accessorContext) ||
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(accessorContext)) {
                    return "ALLOWED";
                } else {
                    return "DENIED";
                }
            case "public":
                return "ALLOWED";
            default:
                return "DENIED";
        }
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println("Test 2: " + classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}
