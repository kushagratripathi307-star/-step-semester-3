package Week6.Assignment.P01_GymMembershipFoundation;

import java.util.Scanner;

public class GymMember {
    private String memberId;
    private int monthlyFee;
    private int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {
        if (memberId == null || memberId.trim().isEmpty() || memberId.length() < 4) {
            throw new IllegalArgumentException("construction rejected");
        }
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        this.sessionsAttended++;
    }

    public int getSessionsAttended() {
        return sessionsAttended;
    }

    public String getMemberId() {
        return memberId;
    }

    public int getMonthlyFee() {
        return monthlyFee;
    }

    public static String signUpBatch(String[] memberIds, int monthlyFee) {
        int signedUp = 0;
        int rejected = 0;

        if (memberIds != null) {
            for (String id : memberIds) {
                try {
                    new GymMember(id, monthlyFee);
                    signedUp++;
                } catch (IllegalArgumentException e) {
                    rejected++;
                }
            }
        }

        return "Signed Up: " + signedUp + " | Rejected: " + rejected;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 1: Gym Membership Foundation & Batch Sign-Up ===");

        // Test 1: Invalid construction rejection
        try {
            GymMember m1 = new GymMember("GM1", 1000);
            System.out.println("Created: " + m1.getMemberId());
        } catch (IllegalArgumentException e) {
            System.out.println("new GymMember(\"GM1\", 1000) -> " + e.getMessage());
        }

        // Test 2: Premium Member session attendance
        PremiumMember p = new PremiumMember("MEM01", 2000, "Coach Riya");
        p.attendSession();
        p.attendSession();
        System.out.println("PremiumMember sessions attended: " + p.getSessionsAttended());

        // Test 3: Batch sign-up validation
        String[] batch = {"MEM1", "GM1", "MEM2", "  ", "MEM3"};
        System.out.println("signUpBatch result: " + signUpBatch(batch, 1000));

        // Interactive Scanner mode
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter Member ID: ");
                String id = scanner.nextLine();
                System.out.print("Enter Monthly Fee: ");
                int fee = Integer.parseInt(scanner.nextLine());
                try {
                    GymMember userMember = new GymMember(id, fee);
                    System.out.println("Successfully created member: " + userMember.getMemberId());
                } catch (IllegalArgumentException e) {
                    System.out.println("Creation failed: " + e.getMessage());
                }
            }
        }
    }
}

class PremiumMember extends GymMember {
    private String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }
}
