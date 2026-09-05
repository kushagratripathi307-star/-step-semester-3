package Week4.Practice.P05_AccountBatchPayments;

class FeeAccount {
    public void pay(double amount) {
        System.out.println("Paid in one go (day-scholar account)");
    }
}

class HostelFeeAccount extends FeeAccount {
    @Override
    public void pay(double amount) {
        System.out.println("Paid in two installments (hostel account)");
    }
}

public class AccountBatchPayments {

    private static int hostelCount = 0;
    private static int dayScholarCount = 0;

    public static void processPayment(FeeAccount account, double amount) {
        if (account instanceof HostelFeeAccount) {
            hostelCount++;
            account.pay(amount);
        } else if (account instanceof FeeAccount) {
            dayScholarCount++;
            account.pay(amount);
        }
    }

    public static void main(String[] args) {
        FeeAccount[] accounts = {
            new HostelFeeAccount(),
            new HostelFeeAccount(),
            new FeeAccount(),
            new FeeAccount()
        };

        double amount = 60000;

        for (FeeAccount acc : accounts) {
            processPayment(acc, amount);
        }

        System.out.printf("Hostel accounts processed: %d | Day-scholar accounts processed: %d\n",
                hostelCount, dayScholarCount);
    }
}
