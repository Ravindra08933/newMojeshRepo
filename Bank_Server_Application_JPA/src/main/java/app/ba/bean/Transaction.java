package app.ba.bean;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;

    @ManyToOne
    @JoinColumn(name="account_number")
    private Account account;

    private LocalDate transactionDate;

    private String transactionType;

    private double amount;
    public int getTransactionId() {
        return transactionId;
    }
    public Transaction() {
    }
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public LocalDate getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public Transaction(LocalDate transactionDate, String transactionType, double amount,Account account) {
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.amount = amount;
        this.account = account;
    }
    @Override
    public String toString() {
        return
                "transactionId=" + transactionId +
                ", account=" + account +
                ", transactionDate=" + transactionDate +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount;
    }
}
