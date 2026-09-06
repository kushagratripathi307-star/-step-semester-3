package Week5.Assignment.P05_ImmutableLoanReceipt;

import java.util.Arrays;

class ReferenceOnlyLoanReceipt extends LoanReceipt {
    private final String roomNumber;

    public ReferenceOnlyLoanReceipt(String memberId, String[] bookIds, String roomNumber) {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }
}

public class CirculationLedger {
    private static String systemBranchCode;

    static {
        systemBranchCode = "PAGETURNER-MAIN-01";
    }

    public static String getSystemBranchCode() {
        return systemBranchCode;
    }

    public static String processNightlyCirculation(LoanReceipt[] receipts) {
        int processed = 0;
        int nullSkipped = 0;
        int referenceOnly = 0;
        int regular = 0;

        if (receipts != null) {
            for (LoanReceipt receipt : receipts) {
                if (receipt == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (receipt instanceof ReferenceOnlyLoanReceipt) {
                        referenceOnly++;
                    } else {
                        regular++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + referenceOnly + " reference-only | " + regular + " regular";
    }

    public static void main(String[] args) {
        // Test 1: Immutability and defensive copying
        LoanReceipt r = new LoanReceipt("LIB-8841", new String[]{"BK-100", "BK-101"});
        String[] ids = r.getBookIds();
        ids[0] = "HACKED";
        System.out.println("Test 1 Defensive Copy: " + r.getBookIds()[0]);

        // Test 2: Wither method
        LoanReceipt corrected = r.withCorrectedBookId(1, "BK-102");
        System.out.println("Test 2 Original Book IDs:  " + Arrays.toString(r.getBookIds()));
        System.out.println("Test 2 Corrected Book IDs: " + Arrays.toString(corrected.getBookIds()));

        // Test 3: Nightly circulation processing
        LoanReceipt[] receipts = new LoanReceipt[]{
            new ReferenceOnlyLoanReceipt("LIB-001", new String[]{"BK-200"}, "Reading Room 3"),
            null,
            new LoanReceipt("LIB-002", new String[]{"BK-201"})
        };
        System.out.println("Test 3 Circulation Processing: " + processNightlyCirculation(receipts));
    }
}
