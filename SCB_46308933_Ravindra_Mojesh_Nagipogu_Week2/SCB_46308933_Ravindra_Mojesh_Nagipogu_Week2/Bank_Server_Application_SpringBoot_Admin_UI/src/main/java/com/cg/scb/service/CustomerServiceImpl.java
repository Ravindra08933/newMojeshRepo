package com.cg.scb.service;

import com.cg.scb.dto.Account;
import com.cg.scb.dto.Admin;
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

    @Override
    public Admin findAdmin(String name) {
        ResponseEntity<Admin>
                response =restTemplate.exchange(
                "http://localhost:8086/login/getadmin/"+name,
                HttpMethod.GET,null,new ParameterizedTypeReference<Admin>(){});
        return response.getBody();
    }

    @Override
    public Customer findCustomerById(Integer customerId) {
        ResponseEntity<Customer>
                response =restTemplate.exchange(
                "http://localhost:8086/login/findcustomer/"+customerId,
                HttpMethod.GET,null,new ParameterizedTypeReference<Customer>(){});
        return response.getBody();
    }

    @Override
    public String deleteCustomer(Integer customerId) {
        ResponseEntity<String>
                response =restTemplate.exchange(
                "http://localhost:8086/login/deletecustomer/"+customerId,
                HttpMethod.GET,null,new ParameterizedTypeReference<String>(){});
        return response.getBody();
    }

    @Override
    public String updateCustomerName(int customerId, String newName) {
        ResponseEntity<String>
                response =restTemplate.exchange(
                "http://localhost:8086/login/update/name/"+customerId+"/"+newName,
                HttpMethod.POST,null,new ParameterizedTypeReference<String>(){});
        return response.getBody();
    }

    @Override
    public String updateCustomerEmail(int customerId, String newEmail) {
        ResponseEntity<String>
                response =restTemplate.exchange(
                "http://localhost:8086/login/update/email/"+customerId+"/"+newEmail,
                HttpMethod.POST,null,new ParameterizedTypeReference<String>(){});
        return response.getBody();
    }

}
