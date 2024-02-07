package app.ba.dao;

import app.ba.bean.Account;
import app.ba.bean.Customer;
import java.util.List;
import java.util.Set;
public interface AccountDao {
    Account findAccountByNumber(int accountNumber);
    Account findAccountByCustomerId(int customerId);
    Account createAccount(Account account);
    String deleteAccount(int accountNumber);
    String deposit(int accountNumber, double amount);
    String withdraw(int accountNumber, double amount);
    String transfer(int fromAccountNumber, int toAccountNumber, double amount);
}
