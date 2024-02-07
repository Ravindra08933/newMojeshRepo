package app.ba.bean;
import java.time.LocalDate;
public class Transaction {

    private int transactionId;
    private LocalDate timestamp;
    private String TransactionType;
    private double amount;
    public Transaction() {
    }
    public Transaction(int transactionId, LocalDate timestamp, String transactionType, double amount) {
        this.transactionId = transactionId;
        this.timestamp = timestamp;
        this.TransactionType = transactionType;
        this.amount = amount;
    }
    public int getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
    public LocalDate getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }
    public String getTransactionType() {
        return TransactionType;
    }
    public void setTransactionType(String TransactionType) {
        this.TransactionType = Transaction.this.TransactionType;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return
                "transactionId=" + transactionId +
                ", timestamp=" + timestamp +
                ", TransactionType='" + TransactionType + '\'' +
                ", amount=" + amount ;
    }
}
