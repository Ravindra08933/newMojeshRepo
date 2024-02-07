package com.cg.bankapp;

import com.cg.bankapp.entity.Account;
import com.cg.bankapp.entity.Transaction;
import com.cg.bankapp.repository.TransactionRepository;
import com.cg.bankapp.service.TransactionServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class TransactionServiceImplTest {
    @Mock
    private TransactionRepository transactionRepository;
    @InjectMocks
    private TransactionServiceImpl transactionService;
    @Test
    void testGetTransactionByType() {
        int accountNumber = 1;
        String transactionType = "Deposit";
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, new Account(accountNumber, null, 0.0, "Savings"), LocalDate.now(), transactionType, 100.0));
        when(transactionRepository.findByAccount_AccountNumber(accountNumber)).thenReturn(transactions);
        List<Transaction> result = transactionService.getTransactionByType(accountNumber, transactionType);
        assertNotNull(result);
        assertEquals(transactions, result);
    }
}
