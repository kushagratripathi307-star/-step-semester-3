package Week5.Assignment.P01_MembershipFieldReachChecker;

class LibraryMember {
    private String membershipPin;
    String branchCode; // default (package-private)
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipPin, String branchCode, double finesOwed, String displayName) {
        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
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

    public static String summarizeByModifier(String[][] attempts) {
        int privateAllowed = 0, privateDenied = 0;
        int defaultAllowed = 0, defaultDenied = 0;
        int protectedAllowed = 0, protectedDenied = 0;
        int publicAllowed = 0, publicDenied = 0;

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt != null && attempt.length >= 2) {
                    String mod = attempt[0];
                    String ctx = attempt[1];
                    String res = classifyAccess(mod, ctx);
                    boolean isAllowed = "ALLOWED".equals(res);

                    switch (mod) {
                        case "private":
                            if (isAllowed) privateAllowed++; else privateDenied++;
                            break;
                        case "default":
                            if (isAllowed) defaultAllowed++; else defaultDenied++;
                            break;
                        case "protected":
                            if (isAllowed) protectedAllowed++; else protectedDenied++;
                            break;
                        case "public":
                            if (isAllowed) publicAllowed++; else publicDenied++;
                            break;
                    }
                }
            }
        }

        return "private: " + privateAllowed + " allowed / " + privateDenied + " denied | " +
               "default: " + defaultAllowed + " allowed / " + defaultDenied + " denied | " +
               "protected: " + protectedAllowed + " allowed / " + protectedDenied + " denied | " +
               "public: " + publicAllowed + " allowed / " + publicDenied + " denied";
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + classifyAccess("private", "SAME_CLASS"));
        System.out.println("Test 2: " + classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };
        System.out.println("Test 3: " + summarizeByModifier(attempts));
    }
}
