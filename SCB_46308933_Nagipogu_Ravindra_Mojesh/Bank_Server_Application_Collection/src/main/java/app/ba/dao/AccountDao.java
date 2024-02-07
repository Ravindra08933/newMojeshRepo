package app.ba.dao;

import app.ba.bean.Account;
import app.ba.bean.Customer;
import java.util.List;
import java.util.Set;
public interface AccountDao {
    Account findAccountByNumber(int accountNumber);
    Account findAccountByCustomerId(int customerId);
    Set<Account> getAllAccounts();
    Account createAccount(Customer customer, double amount);
    String deleteAccount(int accountNumber);
    Customer updateAccount(int accNum, String name);
    Customer updateAccountEmail(int accNum, String name);
}
