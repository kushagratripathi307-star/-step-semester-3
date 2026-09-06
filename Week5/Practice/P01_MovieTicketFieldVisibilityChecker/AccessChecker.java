package Week5.Practice.P01_MovieTicketFieldVisibilityChecker;

class MovieTicket {
    private String seatNumber;
    String screenId; // default / package-private
    protected double ticketPrice;
    public String movieTitle;

    public MovieTicket(String seatNumber, String screenId, double ticketPrice, String movieTitle) {
        this.seatNumber = seatNumber;
        this.screenId = screenId;
        this.ticketPrice = ticketPrice;
        this.movieTitle = movieTitle;
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
                return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";
            case "public":
                return "ALLOWED";
            default:
                return "DENIED";
        }
    }

    public static String summarizeBatch(String[][] attempts) {
        int allowed = 0;
        int denied = 0;
        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length >= 2) {
                    String res = classifyAccess(attempt[0], attempt[1]);
                    if ("ALLOWED".equals(res)) {
                        allowed++;
                    } else {
                        denied++;
                    }
                }
            }
        }
        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + classifyAccess("private", "SAME_CLASS"));
        System.out.println("Test 2: " + classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println("Test 3: " + summarizeBatch(attempts));
    }
}
