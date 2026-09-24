package Week6.Assignment.P02_ThreeTiersGymMembership;

import java.util.Scanner;

class GymMember {
    private String memberId;
    private int monthlyFee;
    private int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
        this.sessionsAttended = 0;
    }

    public void attendSession() {
        sessionsAttended++;
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

    public String displayInfo() {
        return "Standard Member | Sessions: " + sessionsAttended;
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

    @Override
    public String displayInfo() {
        return "Premium Member | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
    }
}

class EliteMember extends PremiumMember {
    private String lockerNumber;

    public EliteMember(String memberId, int monthlyFee, String trainerName, String lockerNumber) {
        super(memberId, monthlyFee, trainerName);
        this.lockerNumber = lockerNumber;
    }

    public String getLockerNumber() {
        return lockerNumber;
    }

    @Override
    public String displayInfo() {
        return "Elite Member | Trainer: " + getTrainerName() + " | Locker: " + lockerNumber + " | Sessions: " + getSessionsAttended();
    }
}

class GroupClassMember extends GymMember {
    private String className;

    public GroupClassMember(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String displayInfo() {
        return "Group Class Member | Class: " + className + " | Sessions: " + getSessionsAttended();
    }
}

public class GymMembershipTiers {

    public static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof PremiumMember) {
            return "Direct subclass (2 generations deep)";
        } else {
            return "Base class";
        }
    }

    public static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        if (members != null) {
            for (GymMember member : members) {
                if (member != null) {
                    total += member.getSessionsAttended();
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 2: Three Tiers of Gym Membership ===");

        GymMember standard = new GymMember("MEM1", 1000);
        PremiumMember premium = new PremiumMember("MEM2", 2000, "Coach Riya");
        EliteMember elite = new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember group = new GroupClassMember("MEM4", 1500, "Zumba");

        System.out.println(standard.displayInfo());
        System.out.println(premium.displayInfo());
        System.out.println(elite.displayInfo());
        System.out.println(group.displayInfo());

        System.out.println("\n--- Classify Generation ---");
        System.out.println("classifyGeneration(elite): " + classifyGeneration(elite));
        System.out.println("classifyGeneration(group): " + classifyGeneration(group));

        for (int i = 0; i < 3; i++) premium.attendSession();
        for (int i = 0; i < 2; i++) elite.attendSession();
        for (int i = 0; i < 4; i++) group.attendSession();

        GymMember[] list = {premium, elite, group};
        System.out.println("\nTotal Sessions Attended across mixed list: " + getTotalSessionsAttended(list));

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter Locker Number for Elite Member: ");
                String locker = scanner.nextLine();
                EliteMember em = new EliteMember("MEM_USER", 3000, "Coach User", locker);
                System.out.println("Created: " + em.displayInfo());
                System.out.println("Classification: " + classifyGeneration(em));
            }
        }
    }
}
