package com.cg.scb.service;

import com.cg.scb.dto.Account;
import com.cg.scb.dto.Customer;
import com.cg.scb.dto.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Customer createCustomer(String name, String email, String password) {
        System.out.println("Hello");
        ResponseEntity<Customer> response = restTemplate.exchange(
                "http://localhost:8086/registration/"+name+"/"+email+"/"+password,
                HttpMethod.POST,
                null,
                new ParameterizedTypeReference<Customer>(){});
        return response.getBody();
    }


//http://localhost:6898/login/allcustomers
    @Override
    public List<Customer> getAllCustomers() {
        ResponseEntity<List<Customer>>
                response =restTemplate.exchange(
                "http://localhost:8086/login/allcustomers",
                HttpMethod.GET,null,new ParameterizedTypeReference<List<Customer>>(){});
        List<Customer> transactions=response.getBody();
        return transactions ;
    }


}
