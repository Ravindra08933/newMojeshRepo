package com.cg.bankapp.service;

import com.cg.bankapp.entity.Account;
import com.cg.bankapp.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    void createTransaction(Account account, String transactionType, double amount);

    List<Transaction> getTransactionByType(int accNUm, String type);

    List<Transaction> getAllTransactions(int accountNumber);

    List<Transaction> getLast10Transactions(int accountNumber);

    Transaction getTransactionByTransactionId(int accountNum, int transactionId);

    List<Transaction> getPreviousDaysTransactions(int accountNumber, int days);
    void deleteTransactionById(int transactionId);
}
