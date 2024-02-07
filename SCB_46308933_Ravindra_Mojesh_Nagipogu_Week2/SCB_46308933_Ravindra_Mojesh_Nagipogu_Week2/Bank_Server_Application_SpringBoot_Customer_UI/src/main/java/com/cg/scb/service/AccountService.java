package com.cg.scb.service;

import com.cg.scb.dto.Account;
import com.cg.scb.dto.Customer;

public interface AccountService {
    Account findAccountByNumber(int accountNumber);
    String deposit(int accountNumber, double amount);
    double checkBalance(int accountNumber) ;
    String withdraw(int accountNumber, double amount);
    String transfer(int fromAccountNumber, int toAccountNumber, double amount);
}
