package app.ba.bean;
public class Account {

    private int accountNumber;
    private Customer customer;
    private double currentBalance;
    private String accountType;
    private Transaction[] transactions;

    public Account() {
    }
    public Account(int accountNumber, double currentBalance, String accountType, Customer customer) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.currentBalance = currentBalance;
        this.accountType = accountType;
        this.transactions = new Transaction[10];

    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public double getCurrentBalance() {
        return currentBalance;
    }
    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
    public String getAccountType() {
        return accountType;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    public Transaction[] getTransactions() {
        return transactions;
    }
    public void setTransactions(Transaction[] transactions) {
        this.transactions = transactions;
    }
    @Override
    public String toString() {
        return
                "accountNumber=" + accountNumber +
                ", currentBalance=" + currentBalance +
                ", accountType=" + accountType ;
    }
}
