package Week6.Assignment.P03_PremiumLoyaltyDiscountLateFeeLedger;

import java.util.Arrays;
import java.util.Scanner;

class GymMember {
    private String memberId;
    private int monthlyFee;
    private int[] lateFeeHistory;
    private int feeCount;

    public GymMember(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.lateFeeHistory = new int[10];
        this.feeCount = 0;
    }

    protected void chargeLateFee(int amount) {
        if (feeCount < lateFeeHistory.length) {
            lateFeeHistory[feeCount++] = amount;
        }
    }

    public int[] getLateFeeHistory() {
        return Arrays.copyOf(lateFeeHistory, feeCount);
    }

    public int getTotalLateFees() {
        int total = 0;
        for (int i = 0; i < feeCount; i++) {
            total += lateFeeHistory[i];
        }
        return total;
    }

    public String getMemberId() {
        return memberId;
    }
}

class PremiumMember extends GymMember {
    private String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }
}

public class GymLedger {

    public static void main(String[] args) {
        System.out.println("=== Problem 3: Premium Loyalty Discount & Late-Fee Ledger ===");

        // Test 1: PremiumMember halves late fee
        PremiumMember p = new PremiumMember("MEM5", 2000, "Coach Riya");
        p.chargeLateFee(200);
        System.out.println("Total Late Fees after charging 200: " + p.getTotalLateFees()); // Expected: 100

        // Test 2: Defensive copy verification
        int[] history = p.getLateFeeHistory();
        System.out.println("Returned History: " + Arrays.toString(history));
        history[0] = 999; // Attempt to tamper
        System.out.println("History after attempting modification on returned array: " + Arrays.toString(p.getLateFeeHistory()));

        // Test 3: Standard member full late fee
        GymMember std = new GymMember("MEM6", 1000);
        std.chargeLateFee(200);
        System.out.println("Standard Member Total Late Fees: " + std.getTotalLateFees()); // Expected: 200

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter Late Fee Amount to charge Premium Member: ");
                int fee = Integer.parseInt(scanner.nextLine());
                p.chargeLateFee(fee);
                System.out.println("Updated Premium Member Total Fees: " + p.getTotalLateFees());
                System.out.println("Updated Fee History: " + Arrays.toString(p.getLateFeeHistory()));
            }
        }
    }
}
