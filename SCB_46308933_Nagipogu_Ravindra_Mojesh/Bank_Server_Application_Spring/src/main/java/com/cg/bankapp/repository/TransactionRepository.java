package com.cg.bankapp.repository;

import com.cg.bankapp.entity.Account;
import com.cg.bankapp.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    List<Transaction> findByAccount_AccountNumber(int accountNumber);

}
