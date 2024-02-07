package com.cg.bankapp;
import com.cg.bankapp.entity.Account;
import com.cg.bankapp.exception.AccountNotFoundException;
import com.cg.bankapp.repository.AccountRepository;
import com.cg.bankapp.service.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class AccountServiceImplTest
{
    @Mock
    private AccountRepository accountRepository;
    @InjectMocks
    private AccountServiceImpl accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCheckBalance() throws AccountNotFoundException {
        int accountNumber = 1;
        Account existingAccount = new Account();
        existingAccount.setAccountNumber(accountNumber);
        existingAccount.setCurrentBalance(500);
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(existingAccount);
        double result = accountService.checkBalance(accountNumber);
        assertEquals(500.0, result);
    }
    @Test
    void testDeposit() throws AccountNotFoundException {
        int accountNumber = 1;
        double amount = 100;
        Account existingAccount = new Account();
        existingAccount.setAccountNumber(accountNumber);
        existingAccount.setCurrentBalance(500);
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(existingAccount);
        String result = accountService.deposit(accountNumber, amount);
        assertEquals("Deposit successful. New balance: 600.0", result);
    }
    @Test
    void testWithdraw() {
        int accountNumber = 123;
        double initialBalance = 500;
        double withdrawAmount = 100;
        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setCurrentBalance(initialBalance);
        when(accountRepository.findByAccountNumber(accountNumber)).thenReturn(account);
        String result = accountService.withdraw(accountNumber, withdrawAmount);
        assertEquals("Withdraw successful. New balance: " + (initialBalance - withdrawAmount), result);

    }
    @Test
    void testTransfer() {
        int fromAccountNumber = 123;
        int toAccountNumber = 456;
        double initialFromBalance = 500;
        double initialToBalance = 200;
        double transferAmount = 100;
        Account fromAccount = new Account();
        fromAccount.setAccountNumber(fromAccountNumber);
        fromAccount.setCurrentBalance(initialFromBalance);
        Account toAccount = new Account();
        toAccount.setAccountNumber(toAccountNumber);
        toAccount.setCurrentBalance(initialToBalance);
        when(accountRepository.findByAccountNumber(fromAccountNumber)).thenReturn(fromAccount);
        when(accountRepository.findByAccountNumber(toAccountNumber)).thenReturn(toAccount);
        String result = accountService.transfer(fromAccountNumber, toAccountNumber, transferAmount);
        assertEquals("Transfer successful. New balance for account " + fromAccountNumber + ": " +
                (initialFromBalance - transferAmount) + ". New balance for account " + toAccountNumber + ": " +
                (initialToBalance + transferAmount), result);

    }
}
