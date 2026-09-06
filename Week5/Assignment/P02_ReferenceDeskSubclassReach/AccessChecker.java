package Week5.Assignment.P02_ReferenceDeskSubclassReach;

class LibraryMember {
    protected double finesOwed;

    public LibraryMember(double finesOwed) {
        this.finesOwed = finesOwed;
    }
}

class PremiumLibraryMember extends LibraryMember {
    public PremiumLibraryMember(double finesOwed) {
        super(finesOwed);
    }

    public double getFines() {
        return this.finesOwed;
    }
}

public class AccessChecker {

    public static String classifyAccess(String fieldModifier, String accessorContext) {
        // Clean whitespace from context string if any
        String ctx = (accessorContext != null) ? accessorContext.trim() : "";
        switch (fieldModifier) {
            case "private":
                return "SAME_CLASS".equals(ctx) ? "ALLOWED" : "DENIED";
            case "default":
                return ("SAME_CLASS".equals(ctx) || "SAME_PACKAGE".equals(ctx)) ? "ALLOWED" : "DENIED";
            case "protected":
                if ("SAME_CLASS".equals(ctx) ||
                    "SAME_PACKAGE".equals(ctx) ||
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE".equals(ctx)) {
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

    public static String firstDeniedAttempt(String[][] attempts) {
        if (attempts != null) {
            for (int i = 0; i < attempts.length; i++) {
                String[] attempt = attempts[i];
                if (attempt != null && attempt.length >= 2) {
                    String mod = attempt[0].trim();
                    String ctx = attempt[1].trim();
                    String res = classifyAccess(mod, ctx);
                    if ("DENIED".equals(res)) {
                        return mod + " via " + ctx + " (attempt #" + (i + 1) + ")";
                    }
                }
            }
        }
        return "None Denied";
    }

    public static void main(String[] args) {
        String[][] attempts1 = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println("Test 1: " + firstDeniedAttempt(attempts1));

        String[][] attempts2 = {
            {"public", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"},
            {"protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };
        System.out.println("Test 2: " + firstDeniedAttempt(attempts2));
    }
}
