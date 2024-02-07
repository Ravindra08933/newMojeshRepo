package app.ba.bean;
import jakarta.persistence.*;
@Entity
@Table(name = "Accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int accountNumber;

    @OneToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
    public Account() {
    }
    private double currentBalance;
    public Account(double currentBalance, String accountType,Customer customer) {
        this.customer = customer;
        this.currentBalance = currentBalance;
        this.accountType = accountType;
    }
    private String accountType;
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
    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", currentBalance=" + currentBalance +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}