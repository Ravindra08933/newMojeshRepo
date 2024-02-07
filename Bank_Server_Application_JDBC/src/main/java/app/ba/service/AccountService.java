package app.ba.service;
import app.ba.bean.Account;
import app.ba.bean.Customer;

import java.sql.SQLException;
public interface AccountService {
    double checkBalance(int accountNumber) throws SQLException;
    String deposit(int accountNumber, double amount) throws SQLException;
    String withdraw(int accountNumber, double amount) throws SQLException;
    String transfer(int fromAccountNumber, int toAccountNumber, double amount) throws SQLException;
    Account createAccount(String name, String email,double amount) throws SQLException;
    String deleteAccount(int accountNumber) throws SQLException;
    Account createAccount1(int id,double money) throws SQLException;
}
