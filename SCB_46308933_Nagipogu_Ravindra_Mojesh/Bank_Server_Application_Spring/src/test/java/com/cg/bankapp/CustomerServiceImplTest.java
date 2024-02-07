package com.cg.bankapp;

import com.cg.bankapp.entity.Customer;
import com.cg.bankapp.exception.CustomerNotFoundException;
import com.cg.bankapp.repository.CustomerRepository;

import com.cg.bankapp.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateCustomer() {
        String name = "John Doe";
        String email = "john@example.com";
        Customer expectedCustomer = new Customer();
        expectedCustomer.setCustomerId(1);
        expectedCustomer.setName(name);
        expectedCustomer.setEmail(email);
        when(customerRepository.save(any())).thenReturn(expectedCustomer);
        Customer actualCustomer = customerService.createCustomer(name, email);
        assertNotNull(actualCustomer);
        assertEquals(expectedCustomer.getCustomerId(), actualCustomer.getCustomerId());
        assertEquals(expectedCustomer.getName(), actualCustomer.getName());
        assertEquals(expectedCustomer.getEmail(), actualCustomer.getEmail());
    }

    @Test
    void testFindCustomerById() {
        int customerId = 1;
        Customer expectedCustomer = new Customer();
        expectedCustomer.setCustomerId(customerId);
        when(customerRepository.findById(customerId)).thenReturn(java.util.Optional.of(expectedCustomer));
        Customer actualCustomer = customerService.findCustomerById(customerId);
        assertNotNull(actualCustomer);
        assertEquals(expectedCustomer, actualCustomer);
    }

    @Test
    void testFindCustomerByIdNotFound() {
        int customerId = 1;
        when(customerRepository.findById(customerId)).thenReturn(java.util.Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerService.findCustomerById(customerId));
    }
    @Test
    void testFindCustomerByName() {
        String name = "John";
        Customer mockCustomer = new Customer(1, name, "john@example.com");
        when(customerRepository.findByName(name)).thenReturn(mockCustomer);
        Customer foundCustomer = customerService.findCustomerByName(name);
        assertNotNull(foundCustomer);
        assertEquals(mockCustomer, foundCustomer);
    }
    @Test
    void testUpdateCustomerName() {
        int customerId = 1;
        String newName = "NewName";
        Customer existingCustomer = new Customer(customerId, "OldName", "old@example.com");
        when(customerRepository.findById(customerId)).thenReturn(java.util.Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(existingCustomer);
        Customer updatedCustomer = customerService.updateCustomerName(customerId, newName);
        assertNotNull(updatedCustomer);
        assertEquals(newName, updatedCustomer.getName());
    }
    @Test
    void testUpdateCustomerEmail() {
        int customerId = 1;
        String newEmail = "new@example.com";
        Customer existingCustomer = new Customer(customerId, "Name", "old@example.com");
        when(customerRepository.findById(customerId)).thenReturn(java.util.Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(existingCustomer);
        Customer updatedCustomer = customerService.updateCustomerEmail(customerId, newEmail);
        assertNotNull(updatedCustomer);
        assertEquals(newEmail, updatedCustomer.getEmail());
    }
    @Test
    void testDeleteCustomer() {
        int customerId = 1;
        when(customerRepository.existsById(customerId)).thenReturn(true);
        assertDoesNotThrow(() -> customerService.deleteCustomer(customerId));
    }

}
