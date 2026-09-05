package Week2.Practice.P04_MaskedPhoneNumberFormatter;

public class MaskedPhoneNumberFormatter {

    public static String maskPhoneNumber(String phone) {
        if (phone == null || phone.length() != 10) {
            return "Invalid phone number";
        }

        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return "Invalid phone number";
            }
        }

        StringBuilder sb = new StringBuilder("XXXXXX");
        sb.append(phone.substring(6));
        sb.insert(6, "-");

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("\"9876543210\" -> " + maskPhoneNumber("9876543210"));
        System.out.println("\"98765\" -> " + maskPhoneNumber("98765"));
    }
}
