package com.cg.scb.service;

import com.cg.scb.dto.Account;
import com.cg.scb.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Account findAccountByNumber(int accountNumber) {
        ResponseEntity<Account> response = restTemplate.exchange(
                "http://localhost:8086/login/findaccount/" + accountNumber,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Account>(){});
            Account account=response.getBody();
            return account ;
        }

    @Override
    public String deposit(int accountNumber, double amount) {
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8086/login/depositmoney/" + accountNumber+"/"+amount,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    @Override
    public double checkBalance(int accountNumber) {
        return 0;
    }

    @Override
    public String withdraw(int accountNumber, double amount) {
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8086/login/withdrawmoney/" + accountNumber+"/"+amount,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    @Override
    public String transfer(int fromAccountNumber, int toAccountNumber, double amount) {
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8086/login/transfermoney/" + fromAccountNumber+"/"+toAccountNumber+"/"+amount,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }
    @Override
    public Account createAccount(String name,String email,String password,double currentBalance) {
        ResponseEntity<Account> response = restTemplate.exchange(
                "http://localhost:8086/login/accountcreation/" +name+"/"+email+"/"+password+"/"+currentBalance,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();


    }

    @Override
    public String deleteAccount(Integer accountNumber) {
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8086/login/deleteaccount/"+accountNumber,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

    @Override
    public List<Account> getAllAccounts() {
        ResponseEntity<List<Account>> response = restTemplate.exchange(
                "http://localhost:8086/login/allaccounts",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });
        return response.getBody();
    }

}
