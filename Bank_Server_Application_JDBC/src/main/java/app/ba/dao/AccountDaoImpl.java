package app.ba.dao;

import app.Generator.NumberGenerator;
import app.ba.Exception.AccountNotFoundException;
import app.ba.bean.Account;
import app.ba.bean.Customer;
import app.ba.util.JDBCUtil;

import java.sql.*;

import static app.ba.util.JDBCUtil.*;
public class AccountDaoImpl implements AccountDao
{

    private NumberGenerator numberGenerator = new NumberGenerator();

    @Override
    public Account findAccountByNumber(int accountNumber) throws AccountNotFoundException, SQLException {
        String SELECT_ACCOUNT_SQL = "SELECT * FROM account WHERE account_number = ?";
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        PreparedStatement accountStatement = connection.prepareStatement(SELECT_ACCOUNT_SQL);

        accountStatement.setInt(1, accountNumber);
        ResultSet accountResultSet = accountStatement.executeQuery();

        if (accountResultSet.next()) {
            double currentBalance = accountResultSet.getDouble("current_balance");
            String accountType = accountResultSet.getString("account_type");
            Account account = new Account();
            account.setAccountNumber(accountNumber);
            account.setCurrentBalance(currentBalance);
            account.setAccountType(accountType);
            accountResultSet.close();
            return account;
        } else {
            accountResultSet.close();
            throw new AccountNotFoundException("Account Not Found with Account Number " + accountNumber);
        }
    }





    @Override
    public Account findAccountByCustomerId(int customerId) throws AccountNotFoundException, SQLException {
        String SELECT_ACCOUNT_SQL = "SELECT * FROM account WHERE account_number = ?";

        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        PreparedStatement accountStatement = connection.prepareStatement(SELECT_ACCOUNT_SQL);

        accountStatement.setInt(1, customerId);
        ResultSet accountResultSet = accountStatement.executeQuery();

        if (accountResultSet.next()) {
            int accountNumber = accountResultSet.getInt("account_number");
            double currentBalance = accountResultSet.getDouble("current_balance");
            String accountType = accountResultSet.getString("account_type");
            Account account = new Account();
            account.setAccountNumber(accountNumber);
            account.setCurrentBalance(currentBalance);
            account.setAccountType(accountType);
            accountResultSet.close();
            return account;
        } else {
            accountResultSet.close();
            throw new AccountNotFoundException("Account Not Found with Account Number " + customerId);
        }
    }


    @Override
    public Account createAccount(Customer customer, double amount) throws SQLException {
        int accNum = numberGenerator.generateNumber();
        JDBCUtil.createAccount(accNum, amount, "Savings", customer);
        return findAccountByNumber(accNum);
    }

    @Override
    public String deleteAccount(int accountNumber) throws AccountNotFoundException, SQLException {
        return JDBCUtil.deleteAccount(accountNumber);
    }
    @Override
    public void updateBalance(int accountNumber, double newBalance) throws SQLException {
        JDBCUtil.updateBalance(accountNumber,newBalance);
    }
    @Override
    public Account createAccount1(double money,Customer customer) throws SQLException {
        int accNum = numberGenerator.generateNumber();
        JDBCUtil.createAccount1(accNum,money,"Savings",customer);
        return findAccountByNumber(accNum);
    }
}
