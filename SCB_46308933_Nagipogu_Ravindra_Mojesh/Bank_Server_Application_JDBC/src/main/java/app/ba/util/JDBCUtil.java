package app.ba.util;
import app.ba.Exception.AccountNotFoundException;
import app.ba.bean.Customer;

import java.sql.*;
public class JDBCUtil {

        public static final String JDBC_URL = "jdbc:postgresql://localhost:5432/postgres";
        public static final String JDBC_USER = "postgres";
        public static final String JDBC_PASSWORD = "429221";

    public static void createAccount(int accountNumber, double initialBalance, String accountType, Customer customer) throws SQLException {
        String INSERT_CUSTOMER_SQL = "INSERT INTO customer (customer_id, name, email) VALUES (?, ?, ?)";
        String INSERT_ACCOUNT_SQL = "INSERT INTO account (customer_id, account_number, current_balance, account_type) VALUES (?, ?, ?, ?)";

        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

        PreparedStatement preparedStatementCustomer = connection.prepareStatement(INSERT_CUSTOMER_SQL);
        preparedStatementCustomer.setInt(1, customer.getCustomerId());
        preparedStatementCustomer.setString(2, customer.getName());
        preparedStatementCustomer.setString(3, customer.getEmail());
        preparedStatementCustomer.executeUpdate();

        PreparedStatement preparedStatementAccount = connection.prepareStatement(INSERT_ACCOUNT_SQL);
        preparedStatementAccount.setInt(1, customer.getCustomerId());
        preparedStatementAccount.setInt(2, accountNumber);
        preparedStatementAccount.setDouble(3, initialBalance);
        preparedStatementAccount.setString(4, accountType);
        int rowsAffected = preparedStatementAccount.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Account Created Successfully.");
        } else {
            System.out.println("Failed to save customer.");
        }

        preparedStatementCustomer.close();
        preparedStatementAccount.close();
        connection.close();
    }

    public static String deleteAccount(int accountNumber) throws AccountNotFoundException, SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        if (!accountExists(connection, accountNumber)) {
            throw new AccountNotFoundException("Account not found with account number: " + accountNumber);
        }
        String deleteAccountQuery = "DELETE FROM account WHERE account_number = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteAccountQuery);
        preparedStatement.setInt(1, accountNumber);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

        return "Account Deleted Successfully";

    }

    private static boolean accountExists(Connection connection, int accountNumber) throws SQLException {
        String checkAccountQuery = "SELECT COUNT(*) FROM account WHERE account_number = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(checkAccountQuery);
        preparedStatement.setInt(1, accountNumber);
       ResultSet resultSet = preparedStatement.executeQuery() ;
            if (resultSet.next())
                return resultSet.getInt(1) > 0;
                return false;
    }

    public static void updateBalance(int accountNumber, double newBalance) throws SQLException {
        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE account SET current_balance = ? WHERE account_number = ?");

        preparedStatement.setDouble(1, newBalance);
        preparedStatement.setInt(2, accountNumber);

        preparedStatement.executeUpdate();

        connection.close();
    }
    public static void createAccount1(int accountNumber, double initialBalance, String accountType, Customer customer) throws SQLException {
        String INSERT_ACCOUNT_SQL = "INSERT INTO account (customer_id, account_number, current_balance, account_type) VALUES (?, ?, ?, ?)";

        Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

        PreparedStatement preparedStatementAccount = connection.prepareStatement(INSERT_ACCOUNT_SQL);
        preparedStatementAccount.setInt(1, customer.getCustomerId());
        preparedStatementAccount.setInt(2, accountNumber);
        preparedStatementAccount.setDouble(3, initialBalance);
        preparedStatementAccount.setString(4, accountType);
        int rowsAffected = preparedStatementAccount.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Account Created Successfully.");
        } else {
            System.out.println("Failed to save customer.");
        }
        preparedStatementAccount.close();
        connection.close();
    }
}
