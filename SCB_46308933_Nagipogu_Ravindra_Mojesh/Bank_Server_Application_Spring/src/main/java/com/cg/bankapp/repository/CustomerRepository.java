package com.cg.bankapp.repository;

import com.cg.bankapp.entity.Account;
import com.cg.bankapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByName(String name);
}
