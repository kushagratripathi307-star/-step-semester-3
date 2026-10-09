package Week8.Assignment.P04_FitZoneMembershipDesk;

import java.util.Scanner;

enum MembershipStatus {
    ACTIVE("Active"),
    FROZEN("Frozen"),
    EXPIRED("Expired");

    private final String displayName;

    MembershipStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

interface MembershipPlan {
    String getPlanName();
    int getDurationMonths();
    double getDiscountRate();
    default double calculateFee() {
        double baseRatePerMonth = 1000.0;
        double total = baseRatePerMonth * getDurationMonths();
        return total * (1.0 - getDiscountRate());
    }
}

class MonthlyPlan implements MembershipPlan {
    @Override
    public String getPlanName() {
        return "Monthly";
    }

    @Override
    public int getDurationMonths() {
        return 1;
    }

    @Override
    public double getDiscountRate() {
        return 0.0;
    }
}

class QuarterlyPlan implements MembershipPlan {
    @Override
    public String getPlanName() {
        return "Quarterly";
    }

    @Override
    public int getDurationMonths() {
        return 3;
    }

    @Override
    public double getDiscountRate() {
        return 0.10; // 10% off
    }
}

class AnnualPlan implements MembershipPlan {
    @Override
    public String getPlanName() {
        return "Annual";
    }

    @Override
    public int getDurationMonths() {
        return 12;
    }

    @Override
    public double getDiscountRate() {
        return 0.25; // 25% off
    }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Membership {
    private Member member;
    private MembershipPlan plan;
    private MembershipStatus status;

    public Membership(Member member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.status = MembershipStatus.ACTIVE;
        System.out.printf("%s membership created for %s. Fee: ₹%,.2f. Status: %s.%n",
                plan.getPlanName(), member.getName(), plan.calculateFee(), status.getDisplayName());
    }

    public boolean checkIn() {
        if (status == MembershipStatus.ACTIVE) {
            System.out.printf("%s checked in successfully.%n", member.getName());
            return true;
        } else {
            System.out.printf("Check-in denied: %s's membership is %s.%n",
                    member.getName(), status.getDisplayName());
            return false;
        }
    }

    public boolean freeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
            return false;
        }
        if (status == MembershipStatus.FROZEN) {
            System.out.println("Membership is already Frozen.");
            return false;
        }
        this.status = MembershipStatus.FROZEN;
        System.out.printf("%s's membership frozen. Status: %s.%n", member.getName(), status.getDisplayName());
        return true;
    }

    public boolean unfreeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
            return false;
        }
        if (status == MembershipStatus.ACTIVE) {
            System.out.println("Membership is already Active.");
            return false;
        }
        this.status = MembershipStatus.ACTIVE;
        System.out.printf("%s's membership unfrozen. Status: %s.%n", member.getName(), status.getDisplayName());
        return true;
    }

    public void expire() {
        this.status = MembershipStatus.EXPIRED;
        System.out.printf("%s's membership expired. Status: %s.%n", member.getName(), status.getDisplayName());
    }

    public Member getMember() {
        return member;
    }

    public MembershipPlan getPlan() {
        return plan;
    }

    public MembershipStatus getStatus() {
        return status;
    }
}

public class FitZoneMembershipDesk {
    public static void main(String[] args) {
        System.out.println("=== Problem 4: The FitZone Membership Desk ===");

        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        // Asha buys a Quarterly membership
        Membership ashaMem = new Membership(asha, new QuarterlyPlan());

        // Ravi buys a Monthly membership
        Membership raviMem = new Membership(ravi, new MonthlyPlan());

        // Asha checks in
        ashaMem.checkIn();

        // Asha freezes her membership
        ashaMem.freeze();

        // Asha attempts to check in
        ashaMem.checkIn();

        // Ravi's membership expires
        raviMem.expire();

        // Ravi attempts to freeze his membership
        raviMem.freeze();

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter member name: ");
                String name = scanner.nextLine().trim();
                Membership customMem = new Membership(new Member(name), new AnnualPlan());
                customMem.checkIn();
            }
        }
    }
}
