import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Week 10 - Category B - Practice Problem 1
 * Problem: Bank Account Withdrawal System
 *
 * Task:
 * Design a bank account withdrawal system supporting Savings and Current accounts.
 * - Savings Account: Must maintain a minimum balance of 1000 after any withdrawal.
 * - Current Account: Supports overdraft up to 5000 (minimum balance -5000).
 * Initial balances are always positive.
 *
 * Input Format:
 * - Savings <ID> <InitialBalance>
 * - Current <ID> <InitialBalance>
 * - WITHDRAW <ID> <Amount>
 *
 * Output Format:
 * For each WITHDRAW operation:
 * - Successful: "<ID> balance <balance>"
 * - Rejected:   "<ID> rejected: minimum balance 1000" (Savings)
 *               "<ID> rejected: overdraft limit 5000" (Current)
 * - If account not found: "Account not found"
 */
public class BankAccountWithdrawalSystem {

    // Abstract Base Account Class
    public static abstract class Account {
        protected String id;
        protected int balance;

        public Account(String id, int balance) {
            this.id = id;
            this.balance = balance;
        }

        public String getId() {
            return id;
        }

        public int getBalance() {
            return balance;
        }

        public abstract void withdraw(int amount);
    }

    // Savings Account Subclass
    public static class SavingsAccount extends Account {
        public static final int MINIMUM_BALANCE = 1000;

        public SavingsAccount(String id, int balance) {
            super(id, balance);
        }

        @Override
        public void withdraw(int amount) {
            if (balance - amount < MINIMUM_BALANCE) {
                System.out.println(id + " rejected: minimum balance " + MINIMUM_BALANCE);
            } else {
                balance -= amount;
                System.out.println(id + " balance " + balance);
            }
        }
    }

    // Current Account Subclass
    public static class CurrentAccount extends Account {
        public static final int OVERDRAFT_LIMIT = 5000;

        public CurrentAccount(String id, int balance) {
            super(id, balance);
        }

        @Override
        public void withdraw(int amount) {
            if (balance - amount < -OVERDRAFT_LIMIT) {
                System.out.println(id + " rejected: overdraft limit " + OVERDRAFT_LIMIT);
            } else {
                balance -= amount;
                System.out.println(id + " balance " + balance);
            }
        }
    }

    // Banking Service Manager
    public static class Bank {
        private final Map<String, Account> accounts = new HashMap<>();

        public void processCommand(String line) {
            if (line == null || line.trim().isEmpty()) {
                return;
            }
            String[] parts = line.trim().split("\\s+");
            String command = parts[0];

            if (command.equalsIgnoreCase("Savings")) {
                if (parts.length >= 3) {
                    String id = parts[1];
                    int initialBalance = Integer.parseInt(parts[2]);
                    accounts.put(id, new SavingsAccount(id, initialBalance));
                }
            } else if (command.equalsIgnoreCase("Current")) {
                if (parts.length >= 3) {
                    String id = parts[1];
                    int initialBalance = Integer.parseInt(parts[2]);
                    accounts.put(id, new CurrentAccount(id, initialBalance));
                }
            } else if (command.equalsIgnoreCase("WITHDRAW")) {
                if (parts.length >= 3) {
                    String id = parts[1];
                    int amount = Integer.parseInt(parts[2]);
                    Account account = accounts.get(id);
                    if (account == null) {
                        System.out.println("Account not found");
                    } else {
                        account.withdraw(amount);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // If standard input contains commands, process them directly.
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            Bank bank = new Bank();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() || line.equalsIgnoreCase("EXIT")) {
                    break;
                }
                bank.processCommand(line);
            }
        } else {
            // Default demonstration executing the exact sample from the PDF
            System.out.println("=== Bank Account Withdrawal System Demo ===");
            String[] sampleOperations = {
                    "Savings S1 5000",
                    "WITHDRAW S1 4500",
                    "Current C1 2000",
                    "WITHDRAW C1 6000",
                    "WITHDRAW S1 3000",
                    "WITHDRAW C1 2000"
            };

            Bank bank = new Bank();
            for (String op : sampleOperations) {
                bank.processCommand(op);
            }
        }
    }
}
