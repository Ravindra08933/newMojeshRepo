package com.cg.scb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private int accountNumber;
    private Customer customer;

    private double currentBalance;

    private String accountType;

    @Override
    public String toString() {
        return
                "accountNumber=" + accountNumber +
                ", currentBalance=" + currentBalance +
                ", accountType='" + accountType;
    }
}
