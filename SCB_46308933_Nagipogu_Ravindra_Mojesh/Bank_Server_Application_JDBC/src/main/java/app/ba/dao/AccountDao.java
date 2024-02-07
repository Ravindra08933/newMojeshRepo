package app.ba.dao;

import app.ba.bean.Account;
import app.ba.bean.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
public interface AccountDao {
    Account findAccountByNumber(int accountNumber) throws SQLException;
    Account findAccountByCustomerId(int customerId) throws SQLException;
    Account createAccount(Customer customer, double amount) throws SQLException;
    String deleteAccount(int accountNumber) throws SQLException;
    void updateBalance(int accountNumber, double newBalance) throws SQLException;
    Account createAccount1(double money,Customer customer) throws SQLException;
}
