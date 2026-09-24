package Week6.Assignment.P05_MembershipNumbersReferralCodesCheckIn;

import java.util.Scanner;

class GymMember {
    private static int counter = 2000;
    private static int enrolledCount = 0;

    public final String membershipNumber;
    private int monthlyFee;
    private int feesPaid;

    public GymMember(int monthlyFee) {
        counter++;
        enrolledCount++;
        this.membershipNumber = "GYM-" + counter;
        this.monthlyFee = monthlyFee;
        this.feesPaid = 0;
    }

    public static int getMembersEnrolled() {
        return enrolledCount;
    }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }
        if (code.charAt(0) != 'G') {
            return false;
        }
        if (!Character.isDigit(code.charAt(1)) || !Character.isDigit(code.charAt(2))) {
            return false;
        }
        if (!Character.isUpperCase(code.charAt(3))) {
            return false;
        }
        return true;
    }

    public void payFee(int amount) {
        this.feesPaid += amount;
    }

    public void payFee(int amount, String mode) {
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public int getMonthlyFee() {
        return monthlyFee;
    }
}

class GroupClassMember extends GymMember {
    private String className;

    public GroupClassMember(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}

public class CheckInSettlement {

    public static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int groupCount = 0;
        int individualCount = 0;

        if (members != null) {
            for (GymMember member : members) {
                if (member == null) {
                    nullSkipped++;
                } else {
                    processed++;
                    if (member instanceof GroupClassMember) {
                        groupCount++;
                    } else {
                        individualCount++;
                    }
                }
            }
        }

        return processed + " processed | " + nullSkipped + " null skipped | " + groupCount + " group | " + individualCount + " individual";
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 5: Membership Numbers, Referral Codes & Settlement ===");

        // Test 1: Unique membership numbers & counter
        GymMember m1 = new GymMember(1000);
        System.out.println("m1.membershipNumber: " + m1.membershipNumber);
        System.out.println("Enrolled count: " + GymMember.getMembersEnrolled());

        // Test 2: Referral code validation
        System.out.println("isValidReferralCode(\"G45B\"): " + GymMember.isValidReferralCode("G45B"));
        System.out.println("isValidReferralCode(\"G4B\"): " + GymMember.isValidReferralCode("G4B"));
        System.out.println("isValidReferralCode(\"X45B\"): " + GymMember.isValidReferralCode("X45B"));

        // Test 3: Overloaded payFee
        m1.payFee(500);
        m1.payFee(500, "UPI");
        System.out.println("m1 total fees paid: " + m1.getFeesPaid());

        // Test 4: Check-in processing with null check
        GymMember[] batch = {
            new GroupClassMember(1500, "Zumba"),
            null,
            new GymMember(1000)
        };
        System.out.println("processWeeklyCheckIn: " + processWeeklyCheckIn(batch));

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter referral code to check: ");
                String ref = scanner.nextLine();
                System.out.println("Valid: " + GymMember.isValidReferralCode(ref));
            }
        }
    }
}
