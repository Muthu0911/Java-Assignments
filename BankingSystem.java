class InvalidTransactionException extends Exception {
    public InvalidTransactionException (String message) {
        super(message);
    }
}
class BankAccount {
    private int balance;
    public BankAccount(int balance) {
        this.balance = balance;
    }
    public synchronized void deposit(int amount) throws InvalidTransactionException {  
                                                                                          // synchronized method to avoid data inconsistency
        if (amount <= 0) {
            throw new InvalidTransactionException("Deposit amount must be positive.");
        }
        balance += amount;
        System.out.println(Thread.currentThread().getName()
                + " deposited Rs." + amount
                + " | Balance = Rs." + balance);
    }
    public synchronized void withdraw(int amount) throws InvalidTransactionException {
        if (amount <= 0) {
            throw new InvalidTransactionException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new InvalidTransactionException("Insufficient balance.");
        }
        balance -= amount;
        System.out.println(Thread.currentThread().getName()
                + " withdrew Rs." + amount
                + " | Balance = Rs." + balance);
    }
    public int getBalance() {
        return balance;
    }
}
class UserTransaction extends Thread {                            // Thread class
    BankAccount account;
    int amount;
    String type;
    public UserTransaction(BankAccount account, int amount, String type) {
        this.account = account;
        this.amount = amount;
        this.type = type;
    }
    public void run() {
        try {
            if (type.equalsIgnoreCase("deposit")) {
                account.deposit(amount);
            }
            else if (type.equalsIgnoreCase("withdraw")) {
                account.withdraw(amount);
            }
            else {
                throw new InvalidTransactionException("Invalid transaction type.");
            }
        }
        catch (InvalidTransactionException e) {                         // User-defined exception handling
            System.out.println(Thread.currentThread().getName()
                    + " Error: " + e.getMessage());
        }
        catch (Exception e) {                                                       // Built-in exception handling
            System.out.println("Built-in Exception: " + e);
        }
    }
}
public class BankingSystem {
    public static void main(String[] args) {                                  // Multiple threads (concurrent users)
        BankAccount account = new BankAccount (1000);
        UserTransaction t1 = new UserTransaction (account, 500, "deposit");
        UserTransaction t2 = new UserTransaction (account, 300, "withdraw");
        UserTransaction t3 = new UserTransaction (account, 1500, "withdraw");
        UserTransaction t4 = new UserTransaction (account, -200, "deposit");
        t1.setName("User-1");
        t2.setName("User-2");
        t3.setName("User-3");
        t4.setName("User-4");
        t1.start();                                          // Start threads
        t2.start();
        t3.start();
        t4.start();
        try {                                                 // wait for threads to finish
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            System.out.println(e);
        }

        System.out.println("\nFinal Balance = Rs." + account.getBalance());
    }
}
