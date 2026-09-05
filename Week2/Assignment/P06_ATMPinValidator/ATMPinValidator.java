package Week2.Assignment.P06_ATMPinValidator;

public class ATMPinValidator {

    public static void checkPinLength(String pin) {
        if (pin != null && pin.length() == 4) {
            System.out.println("PIN length OK.");
        } else {
            System.out.println("Invalid PIN - must be exactly 4 digits.");
        }
    }

    public static void main(String[] args) {
        System.out.print("\"482\" -> ");
        checkPinLength("482");

        System.out.print("\"4820\" -> ");
        checkPinLength("4820");
    }
}
