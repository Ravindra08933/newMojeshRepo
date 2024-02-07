package com.cg.scb.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private int transactionId;
    private Account account;
    private LocalDate transactionDate;
    private String transactionType;
    private double amount;
    @Override
    public String toString() {
        return
                "transactionId=" + transactionId +
                ", transactionDate=" + transactionDate +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                '}';
    }
}
