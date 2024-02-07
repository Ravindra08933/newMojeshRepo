package com.cg.scb.service;

import com.cg.scb.dto.Account;
import com.cg.scb.dto.Admin;
import com.cg.scb.dto.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();
    Admin findAdmin(String name);

    Customer findCustomerById(Integer customerId);

    String deleteCustomer(Integer customerId);

    String updateCustomerName(int customerId, String newName);

    String updateCustomerEmail(int customerId, String newEmail);
}
