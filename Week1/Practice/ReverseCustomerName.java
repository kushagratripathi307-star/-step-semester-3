package Week1.Practice;

public class ReverseCustomerName {

    public static String reverseCustomerName(String customerName) {
        StringBuilder sb = new StringBuilder();
        for (int i = customerName.length() - 1; i >= 0; i--) {
            sb.append(customerName.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String originalName = "Sunil";
        String reversedName = reverseCustomerName(originalName);

        System.out.println("Original Name: " + originalName);
        System.out.println("Reversed Name: " + reversedName);
    }
}
