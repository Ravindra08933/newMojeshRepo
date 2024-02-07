package com.cg.scb.service;

import com.cg.scb.dto.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Transaction> getAllTransactions(int accountNumber) {
        System.out.println("Hello");
        ResponseEntity<List<Transaction>> response = restTemplate.exchange(
                "http://localhost:8086/login/alltransactionsdisplay/{accountNumber}",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Transaction>>() {}, accountNumber);
        List<Transaction> transactions = response.getBody();
        return transactions;
    }

    @Override
    public List<Transaction> getLast10Transactions(int accountNumber) {
        List<Transaction> transactions = getAllTransactions(accountNumber);
        System.out.println(transactions.size());
        if (transactions.size() != 0) {
            int size = transactions.size();
            int startIndex = size > 10 ? size - 10 : 0;
            int endIndex = size;
            return transactions.subList(startIndex, endIndex);
        }
        return null;
    }
}
