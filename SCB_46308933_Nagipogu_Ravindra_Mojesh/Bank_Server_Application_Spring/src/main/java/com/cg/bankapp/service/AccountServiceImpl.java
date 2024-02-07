package com.cg.bankapp.service;


import com.cg.bankapp.entity.Account;
import com.cg.bankapp.entity.Customer;
import com.cg.bankapp.entity.Transaction;
import com.cg.bankapp.exception.AccountNotFoundException;
import com.cg.bankapp.repository.AccountRepository;
import com.cg.numbergenerator.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountServiceImpl implements AccountService
{
    NumberGenerator numberGenerator = new NumberGenerator();
    private AccountRepository accountRepository;
    private TransactionService transactionService;
    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Autowired
    public void setAccountRepository(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String deposit(int accountNumber, double amount) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            double currentBalance = account.getCurrentBalance();
            account.setCurrentBalance(currentBalance + amount);
            accountRepository.save(account);
            transactionService.createTransaction(account,"Deposit",amount);
           return "Deposit successful. New balance: " + account.getCurrentBalance();
        }
        throw new AccountNotFoundException("Account with number " + accountNumber + " not found");
    }

    @Override
    public double checkBalance(int accountNumber) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            return account.getCurrentBalance();
        }
        throw new AccountNotFoundException("Account with number " + accountNumber + " not found");

    }

    @Override
    public String withdraw(int accountNumber, double amount) throws AccountNotFoundException {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account != null) {
            double currentBalance = account.getCurrentBalance();
            account.setCurrentBalance(currentBalance - amount);
            accountRepository.save(account);
            transactionService.createTransaction(account,"Withdraw",amount);
            return "Withdraw successful. New balance: " + account.getCurrentBalance();
        }
        throw new AccountNotFoundException("Account with number " + accountNumber + " not found");
    }

    @Override
    public String transfer(int fromAccountNumber, int toAccountNumber, double amount) throws AccountNotFoundException{
            Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber);
            Account toAccount = accountRepository.findByAccountNumber(toAccountNumber);

            if (fromAccount == null) {
                throw new AccountNotFoundException("Account not found with account number: " + fromAccountNumber);
            }

            if (toAccount == null) {
                throw new AccountNotFoundException("Account not found with account number: " + toAccountNumber);
            }

            if (fromAccount.getCurrentBalance() < amount) {
                return "Insufficient funds for the transfer";
            }

            fromAccount.setCurrentBalance(fromAccount.getCurrentBalance() - amount);
            transactionService.createTransaction(fromAccount,"Transferred",amount);
            toAccount.setCurrentBalance(toAccount.getCurrentBalance() + amount);
            transactionService.createTransaction(toAccount,"Received",amount);
            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);


            return "Transfer successful. New balance for account " + fromAccountNumber + ": " + fromAccount.getCurrentBalance() +
                    ". New balance for account " + toAccountNumber + ": " + toAccount.getCurrentBalance();
    }

    @Override
    public Account createAccount(Customer customer, double currentBalance, String accountType) {
        Account newAccount = new Account();
        newAccount.setCustomer(customer);
        newAccount.setAccountNumber(numberGenerator.generateNumber());
        newAccount.setCurrentBalance(currentBalance);
        newAccount.setAccountType(accountType);
        return accountRepository.save(newAccount);
    }

    @Override
    public String deleteAccount(int accountNumber) throws AccountNotFoundException {
        Account account = accountRepository.findById(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with account number: " + accountNumber));
            List<Transaction> transactionList = transactionService.getAllTransactions(accountNumber);
            for (Transaction transaction : transactionList) {
                if (transaction != null)
                    transactionService.deleteTransactionById(transaction.getTransactionId());
            }
            accountRepository.delete(account);
            return "Account with account number "+ accountNumber+" Deleted Successfully";
    }

    @Override
    public Account findAccountByNumber(int accountNumber) throws AccountNotFoundException {
        return accountRepository.findByAccountNumber(accountNumber);
    }
}
