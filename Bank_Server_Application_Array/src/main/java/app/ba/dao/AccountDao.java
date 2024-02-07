package app.ba.dao;
import app.ba.bean.Account;
import app.ba.bean.Customer;
public interface AccountDao {
    Account findAccountByNumber(int accountNumber);
    Account findAccountByCustomerId(int customerId);
    Account[] getAllAccounts();
    Account createAccount(Customer customer,double amount);
    String deleteAccount(int accountNumber);
    Customer updateAccount(int accNum,String name);
    Customer updateAccountEmail(int accNum,String name);

}
