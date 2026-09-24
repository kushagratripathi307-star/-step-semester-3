package Week6.Assignment.P04_MonthlyAttendanceAnnouncer;

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

    public String displayInfo() {
        return "Standard | Sessions: " + sessionsAttended;
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
        return "Premium | Trainer: " + trainerName + " | Sessions: " + getSessionsAttended();
    }
}

public class AttendanceAnnouncer {

    public static String batchPrint(GymMember[] members) {
        StringBuilder sb = new StringBuilder();
        if (members != null) {
            for (GymMember m : members) {
                if (m != null) {
                    sb.append(m.displayInfo());
                    if (m instanceof PremiumMember) {
                        PremiumMember pm = (PremiumMember) m;
                        sb.append(" [Trainer via downcast: ").append(pm.getTrainerName()).append("]");
                    }
                    sb.append(" | ");
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 4: Monthly Attendance Announcer ===");

        GymMember[] members = {
            new GymMember("MEM6", 1000),
            new PremiumMember("MEM7", 2000, "Coach Riya")
        };

        String announcement = batchPrint(members);
        System.out.println("Announcement Output:");
        System.out.println(announcement);

        // ClassCastException Safety Demonstration
        GymMember plain = new GymMember("MEM8", 1000);
        System.out.println("\nTesting instanceof guard:");
        if (plain instanceof PremiumMember) {
            PremiumMember bad = (PremiumMember) plain;
            System.out.println(bad.getTrainerName());
        } else {
            System.out.println("Safely avoided invalid downcast on plain GymMember.");
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine();
            if (ans.equalsIgnoreCase("y")) {
                System.out.print("Enter Trainer Name for Premium Member: ");
                String trainer = scanner.nextLine();
                GymMember[] userBatch = {
                    new GymMember("MEM_USER1", 1000),
                    new PremiumMember("MEM_USER2", 2000, trainer)
                };
                System.out.println("Batch Print Output: " + batchPrint(userBatch));
            }
        }
    }
}
