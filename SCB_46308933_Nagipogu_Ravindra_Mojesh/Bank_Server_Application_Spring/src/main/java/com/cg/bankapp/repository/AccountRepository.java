package com.cg.bankapp.repository;


import com.cg.bankapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findByAccountNumber(int accountNumber);
}
