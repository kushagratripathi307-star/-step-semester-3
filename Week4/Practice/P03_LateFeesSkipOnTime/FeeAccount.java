package Week4.Practice.P03_LateFeesSkipOnTime;

public class FeeAccount {
    String regNo;
    double totalFees;

    public FeeAccount(String regNo, double totalFees) {
        this.regNo = regNo;
        this.totalFees = totalFees;
    }

    public final double calculateLateFee(int daysLate) {
        return this.totalFees * (daysLate * 0.01);
    }

    public final void printSummary(int daysLate) {
        if (daysLate <= 0) {
            System.out.printf("%s - On time, no late fee\n", regNo);
        } else {
            double lateFee = calculateLateFee(daysLate);
            System.out.printf("%s | Total Fee: Rs %.1f | Late Fee: Rs %.1f\n", regNo, totalFees, lateFee);
        }
    }

    public static void main(String[] args) {
        String[] regNos = {"RA001", "RA002", "RA003", "RA004"};
        double[] totalFees = {200000, 150000, 180000, 220000};
        int[] daysLate = {10, 0, -2, 5};

        for (int i = 0; i < regNos.length; i++) {
            FeeAccount account = new FeeAccount(regNos[i], totalFees[i]);
            account.printSummary(daysLate[i]);
        }
    }
}
