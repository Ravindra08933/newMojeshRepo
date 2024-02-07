package com.cg.scb.service;

import com.cg.scb.dto.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> getAllTransactions(int accountNumber);
    List<Transaction> getLast10Transactions(int accountNumber);

}
