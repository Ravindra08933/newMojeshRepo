package com.cg.bankapp.service;

import com.cg.bankapp.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    Customer createCustomer(String name, String email);
    Customer findCustomerById(int customerId);
    Customer findCustomerByName(String Name);
    Customer updateCustomerName(int customerId,String newName);
    Customer updateCustomerEmail(int customerId,String newEmail);
    void deleteCustomer(int customerId);
}
