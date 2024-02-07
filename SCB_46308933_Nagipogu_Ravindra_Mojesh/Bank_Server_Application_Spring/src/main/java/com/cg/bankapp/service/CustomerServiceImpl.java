package com.cg.bankapp.service;

import com.cg.bankapp.entity.Customer;
import com.cg.bankapp.exception.CustomerNotFoundException;
import com.cg.bankapp.repository.CustomerRepository;
import com.cg.numbergenerator.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService
{

    NumberGenerator numberGenerator = new NumberGenerator();
    private CustomerRepository customerRepository;
    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public Customer createCustomer(String name, String email) {
        Customer newCustomer = new Customer();
        newCustomer.setCustomerId(numberGenerator.generateNumber());
        newCustomer.setName(name);
        newCustomer.setEmail(email);
        return customerRepository.save(newCustomer);
    }
    @Override
    public Customer findCustomerById(int customerId) throws CustomerNotFoundException {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + customerId));
    }

    @Override
    public Customer findCustomerByName(String name) throws CustomerNotFoundException {
        Customer customer = customerRepository.findByName(name);
                if(customer!=null)
                    return customer;
                throw new CustomerNotFoundException("Customer not found with name: " + name);
    }

    @Override
    public Customer updateCustomerName(int customerId, String newName) throws CustomerNotFoundException {
        Customer customer = findCustomerById(customerId);
        if(customer == null)
            throw new CustomerNotFoundException("Customer not found with ID: "+customerId);
        customer.setName(newName);
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomerEmail(int customerId, String newEmail) throws CustomerNotFoundException {
        Customer customer = findCustomerById(customerId);
        if(customer == null)
            throw new CustomerNotFoundException("Customer not found with ID: "+customerId);
        customer.setEmail(newEmail);
        return customerRepository.save(customer);
    }
    @Override
    public void deleteCustomer(int customerId) throws CustomerNotFoundException {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
        } else {
            throw new CustomerNotFoundException("Customer not found with ID: " + customerId);
        }
    }

}
