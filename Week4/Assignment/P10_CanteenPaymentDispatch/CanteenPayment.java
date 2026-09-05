package Week4.Assignment.P10_CanteenPaymentDispatch;

class Payment {
    public double pay(double amount) {
        System.out.printf("Paid (cash): Rs %.1f\n", amount);
        return amount;
    }
}

class CardPayment extends Payment {
    public double payWithProcessingFee(double amount) {
        double total = amount * 1.02; // 2% processing fee
        System.out.printf("Charged (card, incl. fee): Rs %.1f\n", total);
        return total;
    }
}

public class CanteenPayment {

    private static double totalCollected = 0.0;

    public static void processTransaction(Payment payment, double amount) {
        if (payment instanceof CardPayment) {
            CardPayment cardPayment = (CardPayment) payment;
            totalCollected += cardPayment.payWithProcessingFee(amount);
        } else {
            totalCollected += payment.pay(amount);
        }
    }

    public static void main(String[] args) {
        Payment[] payments = {
            new CardPayment(),
            new Payment(),
            new CardPayment(),
            new Payment(),
            new CardPayment()
        };

        double[] amounts = {100, 50, 200, 75, 120};

        for (int i = 0; i < payments.length; i++) {
            processTransaction(payments[i], amounts[i]);
        }

        System.out.printf("Total Collected: Rs %.1f\n", totalCollected);
    }
}
