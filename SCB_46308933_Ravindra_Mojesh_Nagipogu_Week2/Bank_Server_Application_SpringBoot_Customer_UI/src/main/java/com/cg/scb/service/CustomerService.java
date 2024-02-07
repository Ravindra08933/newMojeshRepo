package com.cg.scb.service;

import com.cg.scb.dto.Account;
import com.cg.scb.dto.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(String name, String email, String password);

    List<Customer> getAllCustomers();
}
