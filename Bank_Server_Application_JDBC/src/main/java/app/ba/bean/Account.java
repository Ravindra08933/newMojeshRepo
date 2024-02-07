package app.ba.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
public class Account {

    private int accountNumber;
    private Customer customer;
    private double currentBalance;
    private String accountType;


    public Account() {

    }

    public Account(int accountNumber, double currentBalance, String accountType, Customer customer) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.currentBalance = currentBalance;
        this.accountType = accountType;

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

    @Override
    public String toString() {
        return "accountNumber=" + accountNumber +
                ", currentBalance=" + currentBalance +
                ", accountType=" + accountType;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountNumber == account.accountNumber &&
                Double.compare(account.currentBalance, currentBalance) == 0 &&
                Objects.equals(customer, account.customer) &&
                Objects.equals(accountType, account.accountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber, customer, currentBalance, accountType);
    }
}
