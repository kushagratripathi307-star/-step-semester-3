package Week8.Practice.P05_PaymentProcessingShoppingSystem;

import java.util.*;

enum OrderStatus {
    PENDING("Pending"),
    PAID("Paid"),
    CANCELLED("Cancelled");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

interface PaymentMethod {
    String getMethodName();
    boolean processPayment(Order order);
}

class CreditCardPayment implements PaymentMethod {
    @Override
    public String getMethodName() {
        return "Credit Card";
    }

    @Override
    public boolean processPayment(Order order) {
        return true; // Simulating successful payment
    }
}

class PayPalPayment implements PaymentMethod {
    private boolean simulateSuccess;

    public PayPalPayment() {
        this(true);
    }

    public PayPalPayment(boolean simulateSuccess) {
        this.simulateSuccess = simulateSuccess;
    }

    @Override
    public String getMethodName() {
        return "PayPal";
    }

    @Override
    public boolean processPayment(Order order) {
        return simulateSuccess;
    }
}

class BankTransferPayment implements PaymentMethod {
    @Override
    public String getMethodName() {
        return "Bank Transfer";
    }

    @Override
    public boolean processPayment(Order order) {
        return true;
    }
}

class Order {
    private String orderId;
    private Customer customer;
    private List<OrderItem> items;
    private OrderStatus status;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.PENDING;
        System.out.printf("Order created for %s.%n", customer.getName());
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public boolean pay(PaymentMethod method) {
        if (isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return false;
        }

        System.out.printf("Payment initiated via %s for Order %s.%n", method.getMethodName(), orderId);
        boolean success = method.processPayment(this);
        if (success) {
            this.status = OrderStatus.PAID;
            System.out.printf("Payment for Order %s successful. Order status: %s.%n", orderId, status.getDisplayName());
            return true;
        } else {
            System.out.printf("Payment for Order %s failed. Order status: %s.%n", orderId, status.getDisplayName());
            return false;
        }
    }
}

public class PaymentProcessingShoppingSystem {
    public static void main(String[] args) {
        System.out.println("=== Problem 5: Payment Processing for a Shopping System ===");

        Product prodA = new Product("Product A", 50.0);
        Product prodB = new Product("Product B", 30.0);
        Product prodC = new Product("Product C", 100.0);

        Customer custX = new Customer("Customer X");
        Customer custY = new Customer("Customer Y");
        Customer custZ = new Customer("Customer Z");

        // Customer X creates an order with Product A (qty 2) and Product B (qty 1)
        Order orderX = new Order("X", custX);
        orderX.addItem(prodA, 2);
        orderX.addItem(prodB, 1);

        // Customer X attempts to pay using Credit Card
        orderX.pay(new CreditCardPayment());

        // Customer Y creates an empty order
        Order orderY = new Order("Y", custY);

        // Customer Y attempts to pay for the empty order
        orderY.pay(new CreditCardPayment());

        // Customer Z creates an order with Product C (qty 1)
        Order orderZ = new Order("Z", custZ);
        orderZ.addItem(prodC, 1);

        // Customer Z attempts to pay using PayPal (simulating failure)
        orderZ.pay(new PayPalPayment(false));

        // Interactive Scanner demonstration
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWould you like to test interactive mode? (y/n): ");
        if (scanner.hasNextLine()) {
            String ans = scanner.nextLine().trim();
            if (ans.equalsIgnoreCase("y")) {
                Order orderW = new Order("W", new Customer("Interactive User"));
                orderW.addItem(prodA, 1);
                orderW.pay(new BankTransferPayment());
            }
        }
    }
}
