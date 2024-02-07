package com.cg.scb.service;

import com.cg.scb.dto.Account;
import com.cg.scb.dto.Customer;

import java.util.List;

public interface AccountService {
    Account findAccountByNumber(int accountNumber);
    String deposit(int accountNumber, double amount);
    double checkBalance(int accountNumber) ;
    String withdraw(int accountNumber, double amount);
    String transfer(int fromAccountNumber, int toAccountNumber, double amount);
    Account createAccount(String name,String email,String password,double currentBalance);

    String deleteAccount(Integer accountNumber);
    List<Account> getAllAccounts();
}
