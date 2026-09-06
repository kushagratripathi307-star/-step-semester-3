package Week5.Assignment.P04_LibraryMemberJavaBean;

public class LibraryMember {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    public LibraryMember() {
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        // Write-once property: set only if membershipId is currently null
        if (this.membershipId == null) {
            this.membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premiumMember) {
        this.premiumMember = premiumMember;
    }

    public void setSecurityAnswer(String answer) {
        // One-way transformation for write-only security answer property
        if (answer != null) {
            this.securityAnswer = "TRANSFORMED_" + Integer.toHexString(answer.hashCode());
        } else {
            this.securityAnswer = null;
        }
    }

    public static void main(String[] args) {
        LibraryMember m = new LibraryMember();
        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);

        System.out.println("Test 1 Membership ID: " + m.getMembershipId());
        System.out.println("Test 1 Name:          " + m.getName());
        System.out.println("Test 1 Premium:       " + m.isPremiumMember());

        // Test Write-Once behavior
        m.setMembershipId("FAKE-0000");
        System.out.println("Test 2 Write-Once ID: " + m.getMembershipId());

        // Test Write-Only security answer property
        m.setSecurityAnswer("BlueMountain");
        System.out.println("Test 3 Security Answer set successfully (write-only property, no getter exists).");
    }
}
