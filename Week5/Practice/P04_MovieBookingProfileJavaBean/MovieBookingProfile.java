package Week5.Practice.P04_MovieBookingProfileJavaBean;

public class MovieBookingProfile {
    private String name;
    private boolean confirmed;
    private String otp;

    public MovieBookingProfile() {
    }

    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public static void main(String[] args) {
        System.out.println("Test 1: " + new MovieBookingProfile("Rahul Dev").getName());

        MovieBookingProfile p = new MovieBookingProfile("Rahul Dev");
        p.setConfirmed(true);
        System.out.println("Test 2: " + p.isConfirmed());

        p.setOtp("4471");
        System.out.println("Test 3: OTP set successfully (write-only property, no getter exists).");
    }
}
