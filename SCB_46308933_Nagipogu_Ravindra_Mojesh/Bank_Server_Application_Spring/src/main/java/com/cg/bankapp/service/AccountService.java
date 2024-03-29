package com.cg.bankapp.service;

import com.cg.bankapp.entity.Account;
import com.cg.bankapp.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public interface AccountService
{
    String deposit(int accountNumber, double amount);
    double checkBalance(int accountNumber) ;
    String withdraw(int accountNumber, double amount);
    String transfer(int fromAccountNumber, int toAccountNumber, double amount);
    Account createAccount(Customer customer, double currentBalance, String accountType);
    String deleteAccount(int accountNumber);
    Account findAccountByNumber(int accountNumber);
}
